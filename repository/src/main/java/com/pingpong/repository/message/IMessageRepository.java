package com.pingpong.repository.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository  extends MongoRepository<Message, String> {
}
