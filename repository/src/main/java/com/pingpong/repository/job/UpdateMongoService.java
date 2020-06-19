package com.pingpong.repository.job;

import com.pingpong.repository.message.Message;
import com.pingpong.repository.message.MessageService;
import com.pingpong.repository.message.h2.MessageH2;
import com.pingpong.repository.message.h2.MessageH2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UpdateMongoService {

    private final MessageH2Service messageH2Service;

    private final MessageService messageService;

    public UpdateMongoService(MessageH2Service messageH2Service, MessageService messageService) {
        this.messageH2Service = messageH2Service;
        this.messageService = messageService;
    }

    public void updateMongo() {
        log.info("Iniciando processo de atualização dos dados do mongo");
        try {
            List<MessageH2> dataFromH2 = this.messageH2Service.findAll();

            List<Message> messagesToBeSaved = dataFromH2.stream().map(messageH2 -> new Message(messageH2.getMessage(), messageH2.getType(), messageH2.getCreatedAt())).collect(Collectors.toList());
            this.messageService.saveAll(messagesToBeSaved);
            this.messageH2Service.deleteAll();
            log.info("{} mensagens foram deletadas do H2 e inseridos no Mongo", messagesToBeSaved.size());
        }catch (Exception e) {
            log.error("Falha ao atualizar o mongo {}", e);
        }
    }
}
