package id.hadiyan.usermanagement.auth.controller;

import id.hadiyan.usermanagement.auth.dto.request.LoginRequest;
import id.hadiyan.usermanagement.auth.dto.response.LoginResponse;
import id.hadiyan.usermanagement.auth.service.AuthService;
import id.hadiyan.usermanagement.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        BaseResponse<LoginResponse> responnse = new BaseResponse<>(
                HttpStatus.OK,
                authService.login(request)
        );
        return ResponseEntity.ok(responnse);
    }
}
