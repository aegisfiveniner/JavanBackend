package id.hadiyan.usermanagement.auth.service;

import id.hadiyan.usermanagement.auth.dto.request.LoginRequest;
import id.hadiyan.usermanagement.auth.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
