package com.pingpong.app2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingpong.repository.message.Message;
import com.pingpong.repository.message.MessageService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class PingConsumer {

    private final PongProducer pongProducer;

    private final MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    public PingConsumer(PongProducer pongProducer, MessageService messageService) {
        this.pongProducer = pongProducer;
        this.messageService = messageService;
    }

    @PostConstruct
    public void receivePing() throws IOException, TimeoutException {
        log.info("Abrindo conexão para consumir");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("PING", true, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            log.info("Recebido PING: {}", message);
            this.pongProducer.send();
        };
        channel.basicConsume("PING", true, deliverCallback, consumerTag -> {});
    }
}
