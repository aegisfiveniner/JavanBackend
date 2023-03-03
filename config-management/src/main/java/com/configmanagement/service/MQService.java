package com.configmanagement.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public MQService() {
    }

    public void sendMessage(String key, String message) {
        rabbitTemplate.convertAndSend(key, message);
    }
}