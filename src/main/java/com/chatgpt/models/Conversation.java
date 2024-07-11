package com.chatgpt.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Conversation {
    @Id
    private Long id;

    private String openaiToken;

    private String prompt;

    private String completion;

    private Date createdAt;

    private int promptTokens;

    private int completionTokens;

    private String model;



}
