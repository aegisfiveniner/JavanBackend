package id.hadiyan.taxmanagement.user.mapper.impl;

import id.hadiyan.taxmanagement.enums.Role;
import id.hadiyan.taxmanagement.user.dto.consumer.UserDto;
import id.hadiyan.taxmanagement.user.entities.User;
import id.hadiyan.taxmanagement.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public void toEntityFromDto(User user, UserDto userDto) {
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(Role.valueOf(userDto.getRole()));
    }
}
