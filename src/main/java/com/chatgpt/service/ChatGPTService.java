package com.chatgpt.service;

import com.chatgpt.dao.ChatGPTDAO;
import com.chatgpt.dto.ChatCompletionRequest;
import com.chatgpt.dto.ChatCompletionResponse;
import com.chatgpt.models.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatGPTService {

    final private RestTemplate restTemplate;

    final private ChatGPTDAO dao;

    @Value("${openai.chatgpt.url}")
    private String baseUrl;

    @Value("${openai.model}")
    private String model;

    public String chat(String openaiToken, String prompt) {
        setOpenAiToken(openaiToken);

        ChatCompletionRequest request = new ChatCompletionRequest(prompt, model);

        ChatCompletionResponse response = restTemplate.postForObject(baseUrl, request, ChatCompletionResponse.class);

        if(response != null) {

            Conversation conversation = Conversation.builder()
                    .prompt(prompt)
                    .completion(response.getChoices().get(0).getMessage().getContent())
                    .createdAt(response.getCreated())
                    .completionTokens(response.getUsage().getCompletionTokens())
                    .promptTokens(response.getUsage().getPromptTokens())
                    .openaiToken(openaiToken)
                    .model(response.getModel())
                    .build();

            dao.save(conversation);

            return response.getChoices().get(0).getMessage().getContent();
        }

        return null;
    }

    private void setOpenAiToken(String openaiToken) {
        restTemplate.getInterceptors().add(((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiToken);
            return execution.execute(request,body);
        }));
    }

}
