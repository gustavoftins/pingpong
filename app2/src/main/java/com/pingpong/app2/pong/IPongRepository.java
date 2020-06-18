package com.pingpong.app2.pong;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPongRepository  extends MongoRepository<Pong, String> {
}
