package com.maria.usermanagement.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encodePassword(String password) {
        String encoded = passwordEncoder.encode(password);
        return encoded;
    }
}
