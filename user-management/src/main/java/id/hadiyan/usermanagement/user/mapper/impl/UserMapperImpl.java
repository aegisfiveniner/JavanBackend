package id.hadiyan.usermanagement.user.mapper.impl;

import id.hadiyan.usermanagement.enums.Role;
import id.hadiyan.usermanagement.user.dto.request.UserRequest;
import id.hadiyan.usermanagement.user.dto.response.UserResponse;
import id.hadiyan.usermanagement.user.entities.User;
import id.hadiyan.usermanagement.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponse entityToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public User requestToEntity(UserRequest userRequest, String encryptedPassword) {
        return User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(encryptedPassword)
                .role(Role.valueOf(userRequest.getRole()))
                .build();
    }

    @Override
    public void updateEntity(User user, UserRequest userRequest) {
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setRole(Role.valueOf(userRequest.getRole()));
    }
}