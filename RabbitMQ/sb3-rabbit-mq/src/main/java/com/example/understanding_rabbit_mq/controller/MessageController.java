package com.example.understanding_rabbit_mq.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.understanding_rabbit_mq.services.MessageProducer;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
 private final MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam(defaultValue = "Hello world") String message) {
        messageProducer.sendMessage(message);
        return "Message sent: " + message;
    }
}
