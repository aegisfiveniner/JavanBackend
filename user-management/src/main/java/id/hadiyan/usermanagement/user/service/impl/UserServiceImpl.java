package id.hadiyan.usermanagement.user.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.hadiyan.usermanagement.user.dto.UserConsumerDto;
import id.hadiyan.usermanagement.user.dto.request.UserRequest;
import id.hadiyan.usermanagement.user.dto.response.UserResponse;
import id.hadiyan.usermanagement.user.entities.User;
import id.hadiyan.usermanagement.user.mapper.UserMapper;
import id.hadiyan.usermanagement.user.repository.UserRepository;
import id.hadiyan.usermanagement.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::entityToResponse);
    }

    @Override
    public UserResponse getById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.entityToResponse(user);
    }

    @Override
    public void create(UserRequest userRequest) {
        User user = userMapper.requestToEntity(userRequest, passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        UserConsumerDto produce = new UserConsumerDto("CREATE", userMapper.entityToResponse(user));
        produce(produce);
    }

    @Override
    public void update(String id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userMapper.updateEntity(user, userRequest);
        userRepository.save(user);
        UserConsumerDto produce = new UserConsumerDto("UPDATE", userMapper.entityToResponse(user));
        produce(produce);
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserConsumerDto produce = new UserConsumerDto("DELETE", userMapper.entityToResponse(user));
        userRepository.delete(user);
        produce(produce);
    }

    private void produce(UserConsumerDto dto) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            String jsonString = mapper.writeValueAsString(dto);
            String key = String.format("product:%s", dto.getData().getId());
            kafkaTemplate.send("user-sync", key, jsonString);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
