package id.hadiyan.taxmanagement.user.mapper;

import id.hadiyan.taxmanagement.user.dto.consumer.UserDto;
import id.hadiyan.taxmanagement.user.entities.User;

public interface UserMapper {
    void toEntityFromDto(User user, UserDto userDto);
}
