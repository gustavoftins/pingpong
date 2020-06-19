package com.pingpong.repository.message.h2;

import com.pingpong.repository.message.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageH2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Type type;
    private LocalDateTime createdAt = LocalDateTime.now();

    public MessageH2(String message, Type type) {
        this.message = message;
        this.type = type;
    }
}
