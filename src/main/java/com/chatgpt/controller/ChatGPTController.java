package com.chatgpt.controller;

import com.chatgpt.dto.UserRequestBody;
import com.chatgpt.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatGPTController {

    final private ChatGPTService service;

    @PostMapping("/prompts")
    public ResponseEntity<String> chat(@RequestBody UserRequestBody request) {
        return new ResponseEntity<>(
                service.chat(request.getOpenaiToken(), request.getPrompt()),
                HttpStatus.BAD_REQUEST
        );
    }

//    @GetMapping("/conversations")

}
