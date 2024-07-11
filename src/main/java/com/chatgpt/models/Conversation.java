package com.chatgpt.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String openaiToken;

    private String prompt;

    private String completion;

    private Date createdAt;

    private int promptTokens;

    private int completionTokens;

    private String model;

}
