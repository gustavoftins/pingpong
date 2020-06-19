package com.pingpong.repository.message;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final IMessageRepository iMessageRepository;

    public MessageService(IMessageRepository iMessageRepository) {
        this.iMessageRepository = iMessageRepository;
    }

    public Message save(Message message) {
        return iMessageRepository.save(message);
    }

    public Message findById(String id) {
        return this.iMessageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id não encontrado: " + id));
    }

    public Message findByMessage(String message) {
       return this.iMessageRepository.findByMessage(message);
    }

    public void saveAll(List<Message> messagesToBeSaved) {
        this.iMessageRepository.saveAll(messagesToBeSaved);
    }
}
