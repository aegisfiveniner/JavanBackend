package com.javanbackend.laporanpajak.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.javanbackend.laporanpajak.model.User;
import com.javanbackend.laporanpajak.repository.UserRepository;

@Service
public class KafkaConsumer {
    @Autowired
    private UserRepository userRepository;
    
    @KafkaListener(topics = "javanbackend", groupId = "javanbackend")
    public void consume(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode map = mapper.readTree(message);
            User newUser = User.builder()
                .email(map.get("email").toString().replace("\"", ""))
                .userRole(map.get("userRole").get("name").toString().replace("\"", ""))
            .build();
            userRepository.save(newUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
