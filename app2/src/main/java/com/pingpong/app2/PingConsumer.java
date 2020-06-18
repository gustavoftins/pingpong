package com.pingpong.app2;

import com.pingpong.app2.pong.PongProducer;
import com.pingpong.repository.message.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PingConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingConsumer.class);

    private final PongProducer pongProducer;

    private final MessageService messageService;

    public PingConsumer(PongProducer pongProducer, MessageService messageService) {
        this.pongProducer = pongProducer;
        this.messageService = messageService;
    }

    @RabbitListener(queues = "PING")
    public void receivePing(String ping) {
        LOGGER.info("PING: {}",ping);
        this.pongProducer.send();
    }
}
