package com.pingpong.app1;

import com.pingpong.repository.message.Message;
import com.pingpong.repository.message.MessageService;
import com.pingpong.repository.message.Type;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final MessageService messageService;

    public PingProducer(RabbitTemplate rabbitTemplate, MessageService messageService) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageService = messageService;
    }

    public void sendPing() {
        this.messageService.save(new Message("alo", Type.PING));
        rabbitTemplate.convertAndSend("PING", "", "teste");
    }
}