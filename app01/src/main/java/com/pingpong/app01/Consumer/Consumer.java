package com.pingpong.app01.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "PONG")
public class Consumer {

    @RabbitHandler
    public void receive(String message) {
    }
}
