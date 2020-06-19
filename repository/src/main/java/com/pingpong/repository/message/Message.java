package com.pingpong.repository.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    private String id;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Type messageType;
    private String originPingId;

    public Message(String message, Type type) {
        this.message = message;
        this.messageType = type;
    }

    public Message(String message, Type type, String originPingId) {
        this.message = message;
        this.messageType = type;
        this.originPingId = originPingId;
    }

    public Message(String message, Type type, LocalDateTime createdAt) {
        this.messageType = type;
        this.message = message;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", messageType=" + messageType +
                ", originPingId='" + originPingId + '\'' +
                '}';
    }
}
