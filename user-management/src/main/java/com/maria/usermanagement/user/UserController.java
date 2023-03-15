package com.maria.usermanagement.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maria.usermanagement.base.BaseResponse;
import com.maria.usermanagement.user.dto.UserRequestDto;
import com.maria.usermanagement.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getUsers(Pageable pageable) {
        BaseResponse<Page<UserResponseDto>> response = new BaseResponse<>(HttpStatus.OK, service.getUsers(pageable));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto request) throws JsonProcessingException {
        service.createUser(request);
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.OK, "User created");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserResponseDto dto = service.getUserById(id);
        BaseResponse<UserResponseDto> response = new BaseResponse<>(HttpStatus.OK, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequestDto request, @PathVariable String id) throws JsonProcessingException {
        service.updateUser(request, id);
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.OK, "User updated");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) throws JsonProcessingException {
        service.deleteUser(id);
        BaseResponse<String> response = new BaseResponse<>(HttpStatus.OK, "User deleted");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
