package com.maria.usermanagement.auth;

import com.maria.usermanagement.auth.dto.LoginRequestDto;
import com.maria.usermanagement.auth.dto.LoginResponseDto;
import com.maria.usermanagement.security.CustomUserDetailsService;
import com.maria.usermanagement.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil tokenUtil;

    public LoginResponseDto login(LoginRequestDto userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getEmail());
        final String token = tokenUtil.generateToken(userDetails);

        LoginResponseDto response = new LoginResponseDto();
        response.setToken(token);
        return response;
    }
}
