package com.pingpong.app1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingEndpoint {

    private final PingProducer pingProducer;

    public PingEndpoint(PingProducer pingProducer) {
        this.pingProducer = pingProducer;
    }

    @GetMapping
    public void ping() {
        this.pingProducer.sendPing();
    }
}
