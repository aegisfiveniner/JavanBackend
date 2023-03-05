package id.hadiyan.usermanagement.user.mapper;

import id.hadiyan.usermanagement.user.dto.request.UserRequest;
import id.hadiyan.usermanagement.user.dto.response.UserResponse;
import id.hadiyan.usermanagement.user.entities.User;

public interface UserMapper {
    UserResponse entityToResponse(User user);
    User requestToEntity(UserRequest userRequest, String encryptedPassword);
    void updateEntity(User user, UserRequest userRequest);
}
