package com.pingpong.repository.message;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final IMessageRepository iMessageRepository;

    public MessageService(IMessageRepository iMessageRepository) {
        this.iMessageRepository = iMessageRepository;
    }

    public Message save(Message message) {
        return iMessageRepository.save(message);
    }
}
