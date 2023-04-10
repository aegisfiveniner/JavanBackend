package com.alurkerja.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfo {

    public CustomUserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return userDetails;
    }

    public String getRole() {
        Optional<String> role = getUser().getAuthorities().stream().map(x -> x.getAuthority()).findFirst();
        return role.get();
    }
}