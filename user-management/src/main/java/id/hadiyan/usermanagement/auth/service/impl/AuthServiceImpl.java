package id.hadiyan.usermanagement.auth.service.impl;

import id.hadiyan.usermanagement.auth.dto.request.LoginRequest;
import id.hadiyan.usermanagement.auth.dto.response.LoginResponse;
import id.hadiyan.usermanagement.auth.service.AuthService;
import id.hadiyan.usermanagement.configuration.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final TokenUtil tokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenUtil.generateToken(authentication);
        return new LoginResponse(token);
    }
}
