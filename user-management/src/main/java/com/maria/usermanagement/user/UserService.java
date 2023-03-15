package com.maria.usermanagement.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maria.usermanagement.enumerator.EnumRole;
import com.maria.usermanagement.exception.Exception;
import com.maria.usermanagement.security.CustomUserDetails;
import com.maria.usermanagement.security.UserInfo;
import com.maria.usermanagement.user.dto.UserDto;
import com.maria.usermanagement.user.dto.UserRequestDto;
import com.maria.usermanagement.user.dto.UserResponseDto;
import com.maria.usermanagement.user.model.User;
import com.maria.usermanagement.user.repository.UserRepository;
import com.maria.usermanagement.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordUtil util;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private UserInfo userInfo;

    @Value("${topic.name}")
    private String topic;

    public Page<UserResponseDto> getUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(this::convertEntityToDto);
    }

    @Transactional(rollbackOn = java.lang.Exception.class)
    public void createUser(UserRequestDto request) throws JsonProcessingException {
        Optional<User> userEmail = userRepository.findByEmail(request.getEmail());
        if (userEmail.isPresent()) {
            throw new Exception("Email already used");
        } else {
            User newUser = new User();
            newUser = this.convertDtoToEntity(newUser, request);
            userRepository.save(newUser);
            sendMessage(newUser, "CREATE");
        }
    }

    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        return convertEntityToDto(user);
    }

    @Transactional(rollbackOn = java.lang.Exception.class)
    public void updateUser(UserRequestDto request, String id) throws JsonProcessingException {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

        Optional<User> userEmail = userRepository.findByEmail(request.getEmail());
        if (userEmail.isEmpty() || userEmail.isPresent() && userEmail.get().getId().equals(user.getId())) {
            user = this.convertDtoToEntity(user, request);
            userRepository.save(user);
            sendMessage(user, "UPDATE");
        } else {
            throw new Exception("Email already used");
        }
    }

    @Transactional(rollbackOn = java.lang.Exception.class)
    public void deleteUser(String id) throws JsonProcessingException {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        sendMessage(user, "DELETE");
        userRepository.delete(user);
    }

    public User convertDtoToEntity(User user, UserRequestDto request) {
        EnumRole role = EnumRole.getEnumRole(request.getRole()).orElseThrow(() -> new Exception("Role not found"));

        user.setEmail(request.getEmail());
        user.setPassword(util.encodePassword(request.getPassword()));
        user.setRole(role.getValue());
        return user;
    }

    public UserResponseDto convertEntityToDto(User user) {
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        return response;
    }

    private void sendMessage(User user, String type) throws JsonProcessingException {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setUpdatedBy(userInfo.getEmail());

        if (type.equals("CREATE")) {
            dto.setCreatedBy(userInfo.getEmail());
        }

        boolean isDeleted = false;
        if (type.equals("DELETE")) {
            isDeleted = true;
        }
        dto.setDeleted(isDeleted);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);
        kafkaTemplate.send(topic, jsonString);
    }
}
