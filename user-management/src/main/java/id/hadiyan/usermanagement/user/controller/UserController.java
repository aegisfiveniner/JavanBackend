package id.hadiyan.usermanagement.user.controller;

import id.hadiyan.usermanagement.user.dto.request.UserRequest;
import id.hadiyan.usermanagement.user.dto.response.UserResponse;
import id.hadiyan.usermanagement.user.service.UserService;
import id.hadiyan.usermanagement.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getUser(Pageable pageable){
        BaseResponse<Page<UserResponse>> response = new BaseResponse<>(
                HttpStatus.OK,
                userService.getAll(pageable)
        );
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id){
        BaseResponse<UserResponse> response = new BaseResponse<>(
                HttpStatus.OK,
                userService.getById(id)
        );
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserRequest userRequest){
        userService.create(userRequest);
        BaseResponse<String> response = new BaseResponse<>(
                HttpStatus.CREATED,
                "User created"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UserRequest userRequest){
        userService.update(id, userRequest);
        BaseResponse<String> response = new BaseResponse<>(
                HttpStatus.OK,
                "User updated"
        );
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        userService.delete(id);
        BaseResponse<String> response = new BaseResponse<>(
                HttpStatus.OK,
                "User deleted"
        );
        return ResponseEntity.ok().body(response);
    }
}
