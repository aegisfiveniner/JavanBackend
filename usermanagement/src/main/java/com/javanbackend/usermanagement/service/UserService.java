package com.javanbackend.usermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javanbackend.usermanagement.kafka.KafkaProducer;
import com.javanbackend.usermanagement.model.User;
import com.javanbackend.usermanagement.repository.RoleRepository;
import com.javanbackend.usermanagement.repository.UserRepository;
import com.javanbackend.usermanagement.repository.UserRoleRepository;
import com.javanbackend.usermanagement.request.RegisterBody;
import com.javanbackend.usermanagement.response.UserDto;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public void registerUser(RegisterBody request, Map<String, Object> result, HttpStatus status) {
        User newUser = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(roleRepository.findById(request.getRoleId()).get())
            .userRole(userRoleRepository.findById(request.getUserRoleId()).get())
        .build();

        newUser = userRepository.save(newUser);

        UserDto userDto = modelMapper.map(newUser, UserDto.class);

        kafkaProducer.sendMessage(userDto);
        
        result.put("message", "Register user success.");
        result.put("status", status);
        result.put("data", userDto);
    }

    public void getListUser(Map<String, Object> result, HttpStatus status) {
        List<User> listUser = userRepository.findAll();

        List<UserDto> listUserResponse = new ArrayList<UserDto>();

        for (User user : listUser) {
            UserDto userResponse = modelMapper.map(user, UserDto.class);
            listUserResponse.add(userResponse);
        }

        result.put("message", "Register user success.");
        result.put("status", status);
        result.put("data", listUserResponse);
    }

    @Transactional
    public void updateUser(User existingUserById, RegisterBody registerBody, Map<String, Object> result, HttpStatus status) {
        existingUserById.setEmail(registerBody.getEmail());
        existingUserById.setPassword(passwordEncoder.encode(registerBody.getPassword()));
        existingUserById.setRole(roleRepository.findById(registerBody.getRoleId()).get());
        existingUserById.setUserRole(userRoleRepository.findById(registerBody.getUserRoleId()).get());

        userRepository.save(existingUserById);

        result.put("message", "Update user success.");
        result.put("status", status);
        result.put("data", existingUserById);
    }

    @Transactional
    public void deleteById(Integer userId, Map<String, Object> result, HttpStatus status) {
        userRepository.deleteById(userId);

        result.put("message", "Delete user success.");
        result.put("status", status);
    }
}
