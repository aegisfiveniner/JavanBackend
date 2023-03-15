package com.maria.taxreportmanagement.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maria.taxreportmanagement.user.dto.UserDto;
import com.maria.taxreportmanagement.user.model.User;
import com.maria.taxreportmanagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "${topic.name}")
    public void receiveMessage(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto dto = objectMapper.readValue(message, UserDto.class);

        User user = userRepository.findById(dto.getId()).orElse(null);
        if (dto.isDeleted()) {
            userRepository.delete(user);
        } else {
            if (user == null) {
                user = new User();
                user.setId(dto.getId());
                user.setCreatedBy(dto.getCreatedBy());
                user.setCreatedAt(LocalDateTime.now());
            }

            user.setUpdatedBy(dto.getUpdatedBy());
            user.setUpdatedAt(LocalDateTime.now());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setRole(dto.getRole());
            userRepository.save(user);
        }
    }
}

