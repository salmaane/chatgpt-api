package com.chatgpt.controller;

import com.chatgpt.dto.UserRequestBody;
import com.chatgpt.models.Conversation;
import com.chatgpt.service.ChatGptService;
import com.chatgpt.service.ChatGptServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatGPTController {

    final private ChatGptService service;

    @PostMapping("/prompts")
    public ResponseEntity<String> chat(@RequestBody UserRequestBody request) {
        // service call
        String response = service.chat(
                request.getOpenaiToken(), request.getPrompt(), request.getUsername(), request.getConversationId()
        );
        if(response != null) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/conversations")
    public ResponseEntity<List<Conversation>> conversations(@RequestBody UserRequestBody request) {
        List<Conversation> conversations = service.conversations(request.getOpenaiToken());
        if (conversations != null) {
            return ResponseEntity.ok(conversations);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
