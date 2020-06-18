package com.pingpong.app2.pong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pong")
public class Teste {

    @GetMapping
    public String vo(){
        return "Haaaaa";
    }
}
