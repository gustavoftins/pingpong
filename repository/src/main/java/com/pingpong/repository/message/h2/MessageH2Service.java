package com.pingpong.repository.message.h2;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageH2Service {

    private final IMessageH2Repository iMessageH2Repository;

    public MessageH2Service(IMessageH2Repository iMessageH2Repository) {
        this.iMessageH2Repository = iMessageH2Repository;
    }

    public MessageH2 save(MessageH2 messageH2) {
        return this.iMessageH2Repository.save(messageH2);
    }

    public List<MessageH2> findAll() {
        return this.iMessageH2Repository.findAll();
    }

    public void deleteAll() {
        this.iMessageH2Repository.deleteAll();
    }
}
