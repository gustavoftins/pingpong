package com.pingpong.app1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingpong.repository.message.Message;
import com.pingpong.repository.message.MessageService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class PongConsumer {

    private final MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    public PongConsumer(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostConstruct
    public void receive() throws IOException, TimeoutException {
        log.info("Iniciando método de receber PONG");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("PONG", true, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            log.info("PONG RECEBIDO");
            String message = new String(delivery.getBody(), "UTF-8");
            Message originPing = this.messageService.findByMessage(message);
            log.info("PONG: {}", message);
            log.info("PING: {}", originPing.getMessage());
        };
        channel.basicConsume("PONG", true, deliverCallback, consumerTag -> {});

    }
}