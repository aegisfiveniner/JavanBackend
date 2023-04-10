package com.alurkerja.auth;

import com.alurkerja.core.controller.BaseController;
import com.alurkerja.util.LoginRequestUtil;
import com.alurkerja.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthService service;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestUtil request) {
        String token = service.login(request);
        return this.success(token);
    }
}