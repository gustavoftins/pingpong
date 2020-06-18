package com.pingpong.repository.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "message")
public class Message {

    @Id
    private String id;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Type messageType;

    public Message(String message, Type type) {
        this.message = message;
        this.messageType = type;
    }
}
