package com.example.entity.webSocket.controller;

import com.example.entity.webSocket.domain.Greeting;
import com.example.entity.webSocket.domain.HelloMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
public class GreetingController {


    @SneakyThrows
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {

        Thread.sleep(2000);
        log.info("---- greeting-name ----, view: {}", message.getName());
        return new Greeting("hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");

    }

}
