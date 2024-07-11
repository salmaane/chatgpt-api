package com.chatgpt.dao;

import com.chatgpt.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatGPTDAO extends JpaRepository<Conversation, Long> {

    List<Conversation> findAllByOpenaiToken(String openAiToken);

}
