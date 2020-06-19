package com.pingpong.app2;

import com.pingpong.repository.message.Message;
import com.pingpong.repository.message.MessageService;
import com.pingpong.repository.message.Type;
import com.pingpong.repository.message.h2.MessageH2;
import com.pingpong.repository.message.h2.MessageH2Service;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class PongProducer {

    private final MessageService messageService;

    private final MessageH2Service messageH2Service;

    public PongProducer(MessageService messageService, MessageH2Service messageH2Service) {
        this.messageService = messageService;
        this.messageH2Service = messageH2Service;
    }


    public void send() {
        log.info("Abrindo conexão para enviar PONG");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare("PING", true, false, false, null);

            String mensagem = saveNewMessage();
            channel.basicPublish("", "PONG", null, mensagem.getBytes());
            log.info("PONG enviado: {}", mensagem);
        } catch (TimeoutException e) {
            log.error("Falha ao enviar PONG: {}", e);
        } catch (IOException e) {
            log.error("Falha ao enviar PONG: {}", e);
        }
    }

    private String saveNewMessage() {
        String messageContent = UUID.randomUUID().toString();
        try {
            Message savedMessage = this.messageService.save(new Message(messageContent, Type.PONG));
            log.info("Mensagem salva no mongo com sucesso: {}", savedMessage);
        }catch (Exception e) {
            MessageH2 savedH2Message = this.messageH2Service.save(new MessageH2(messageContent, Type.PONG));
            log.warn("Falha ao enviar mensagem ao Mongo, salvada no H2: {}", savedH2Message);
        }
        return messageContent;
    }
}
