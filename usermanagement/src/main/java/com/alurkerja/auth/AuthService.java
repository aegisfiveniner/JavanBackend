package com.alurkerja.auth;

import com.alurkerja.crud.user.UserDto;
import com.alurkerja.security.CustomUserDetailsService;
import com.alurkerja.util.LoginRequestUtil;
import com.alurkerja.util.TokenUtil;
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
    private TokenUtil tokenUtil;

    public String login(LoginRequestUtil userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getEmail());
        final String token = tokenUtil.generateToken(userDetails);

        return token;
    }
}
