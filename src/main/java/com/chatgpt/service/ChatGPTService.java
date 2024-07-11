package com.chatgpt.service;

import com.chatgpt.dao.ChatGPTDAO;
import com.chatgpt.dto.ChatCompletionRequest;
import com.chatgpt.dto.ChatCompletionResponse;
import com.chatgpt.models.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatGPTService {

    final private ChatGPTDAO dao;

    @Value("${openai.chatgpt.url}")
    private String baseUrl;

    @Value("${openai.model}")
    private String model;

    public String chat(String openaiToken, String prompt) {
        RestTemplate restTemplate = getRestTemplateWithTokenSet(openaiToken);

        ChatCompletionRequest request = new ChatCompletionRequest(prompt, model);

        ChatCompletionResponse response = restTemplate.postForObject(baseUrl, request, ChatCompletionResponse.class);

        if(response != null) {

            Conversation conversation = Conversation.builder()
                    .prompt(prompt)
                    .completion(response.getChoices().get(0).getMessage().getContent())
                    .createdAt(response.getCreated())
                    .completionTokens(response.getUsage().getCompletion_tokens())
                    .promptTokens(response.getUsage().getPrompt_tokens())
                    .openaiToken(openaiToken)
                    .model(response.getModel())
                    .build();

            dao.save(conversation);

            return response.getChoices().get(0).getMessage().getContent();
        }

        return null;
    }

    public List<Conversation> conversations(String openAiToken) {
        return dao.findAllByOpenaiToken(openAiToken);
    }

    private RestTemplate getRestTemplateWithTokenSet(String openaiToken) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiToken);
            return execution.execute(request,body);
        }));
        return restTemplate;
    }

}
