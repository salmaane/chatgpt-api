package com.chatgpt.service;

import com.chatgpt.dao.UserDao;
import com.chatgpt.dto.ChatCompletionRequest;
import com.chatgpt.dto.ChatCompletionResponse;
import com.chatgpt.models.Conversation;
import com.chatgpt.models.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatGPTService {


    @Value("${openai.chatgpt.url}")
    private String baseUrl;

    @Value("${openai.model}")
    private String model;
    private final UserDao userDao;


    public String chat(String openaiToken, String prompt , String userName) {
        RestTemplate restTemplate = getRestTemplateWithTokenSet(openaiToken);

        ChatCompletionRequest request = new ChatCompletionRequest(prompt, model);

        ChatCompletionResponse response = restTemplate.postForObject(baseUrl, request, ChatCompletionResponse.class);

        if(response != null) {

           var user =  userDao.findByOpenaiToken(openaiToken);
           if(user != null) {
               Prompt prompt1 = Prompt.builder().build();
              // user.getConversations().add();
           }

            return response.getChoices().get(0).getMessage().getContent();


        }

        return null;
    }

    public List<Conversation> conversations(String openAiToken) {
        //return dao.findAllByOpenaiToken(openAiToken);
        return null;
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
