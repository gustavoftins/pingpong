package com.pingpong.app2.pong;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
@Data
@Getter
@Setter
public class Pong {

    @Id
    private String id;
    private String message;
}
