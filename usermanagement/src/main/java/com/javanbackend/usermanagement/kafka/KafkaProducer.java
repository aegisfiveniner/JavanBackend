package com.javanbackend.usermanagement.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.javanbackend.usermanagement.response.UserDto;

@Service
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserDto userDto) {
        Message<UserDto> message =
            MessageBuilder
                .withPayload(userDto)
                .setHeader(KafkaHeaders.TOPIC, "javanbackend")
                .build();
        kafkaTemplate.send(message);
    }
}
