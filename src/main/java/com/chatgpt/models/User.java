package com.chatgpt.models;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "api_token", unique = true, nullable = false)
    private String openaiToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Conversation> conversations;

    // Getters and setters

    // Constructors

    // toString, equals, and hashCode methods
}