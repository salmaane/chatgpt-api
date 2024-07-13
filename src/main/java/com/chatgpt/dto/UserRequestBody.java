package com.chatgpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBody {

    private String openaiToken;
    private String prompt;
    private String username;
    private Long conversationId; // optional


}
