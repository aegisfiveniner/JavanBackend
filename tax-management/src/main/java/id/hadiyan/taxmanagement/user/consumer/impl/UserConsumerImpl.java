package id.hadiyan.taxmanagement.user.consumer.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.hadiyan.taxmanagement.user.consumer.UserConsumer;
import id.hadiyan.taxmanagement.user.dto.consumer.UserConsumerDto;
import id.hadiyan.taxmanagement.user.dto.consumer.UserDto;
import id.hadiyan.taxmanagement.user.entities.User;
import id.hadiyan.taxmanagement.user.mapper.UserMapper;
import id.hadiyan.taxmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConsumerImpl implements UserConsumer {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @KafkaListener(topics = "user-sync")
    public void consume(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserConsumerDto dto = mapper.readValue(message, UserConsumerDto.class);
            if (dto.getAction().equalsIgnoreCase("CREATE") || dto.getAction().equalsIgnoreCase("UPDATE")) {
                processUpsert(dto.getData());
            } else if (dto.getAction().equalsIgnoreCase("DELETE")) {
                processDelete(dto.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processUpsert(UserDto dto) {
        User user = userRepository.findById(dto.getId()).orElse(new User());
        userMapper.toEntityFromDto(user, dto);
        userRepository.save(user);
    }

    private void processDelete(UserDto dto) {
        User user = userRepository.findById(dto.getId()).orElse(null);
        if (user == null) {
            return;
        }
        userRepository.delete(user);
    }
}
