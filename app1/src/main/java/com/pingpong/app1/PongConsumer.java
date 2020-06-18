package com.pingpong.app1;

import com.pingpong.repository.message.Message;
import com.pingpong.repository.message.MessageService;
import com.pingpong.repository.message.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PongConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PongConsumer.class);

    private final MessageService messageService;

    public PongConsumer(MessageService messageService) {
        this.messageService = messageService;
    }

    @RabbitListener(queues = "PONG")
    public void receive(String pong) {
        Message saved = messageService.save(new Message("teste", Type.PING));
        LOGGER.info("PONG: {}", pong);
    }
}