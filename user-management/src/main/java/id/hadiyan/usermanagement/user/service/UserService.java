package id.hadiyan.usermanagement.user.service;

import id.hadiyan.usermanagement.user.dto.request.UserRequest;
import id.hadiyan.usermanagement.user.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> getAll(Pageable pageable);
    UserResponse getById(String id);
    void create(UserRequest userRequest);
    void update(String id, UserRequest userRequest);
    void delete(String id);
}
