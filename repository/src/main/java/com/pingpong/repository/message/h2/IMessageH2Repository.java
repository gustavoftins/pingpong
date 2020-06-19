package com.pingpong.repository.message.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageH2Repository extends JpaRepository<MessageH2, Long> {
}
