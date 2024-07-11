package com.chatgpt.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatCompletionRequest {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.temperature}")
    private Double temperature;

    @Value("${openai.maxTokens}")
    private Double maxTokens;

    @Value("${openai.topP}")
    private Double topP;

    @Value("${openai.frequencyPenalty}")
    private Double frequencyPenalty;

    @Value("${openai.presencePenalty}")
    private Double presencePenalty;

    private List<Message> messages;

    public ChatCompletionRequest(String prompt) {
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}
