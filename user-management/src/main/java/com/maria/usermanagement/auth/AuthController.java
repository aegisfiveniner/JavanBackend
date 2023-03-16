package com.maria.usermanagement.auth;

import com.maria.usermanagement.auth.dto.LoginRequestDto;
import com.maria.usermanagement.auth.dto.LoginResponseDto;
import com.maria.usermanagement.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto dto = service.login(request);
        BaseResponse<LoginResponseDto> response = new BaseResponse<>(HttpStatus.OK, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
