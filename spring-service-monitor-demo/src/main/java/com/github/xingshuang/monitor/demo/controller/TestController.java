package com.github.xingshuang.monitor.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShuangPC
 * @date 2020/6/2
 */
@RestController
public class TestController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/dest/test")
    @SendTo("/topic/message/hello")
    public String executeTrade(Message message) {
        // ...
        return "hello";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message)throws Exception{
        return message;
    }
}
