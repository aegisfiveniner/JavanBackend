package com.javanbackend.usermanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javanbackend.usermanagement.model.User;
import com.javanbackend.usermanagement.repository.UserRepository;

@Service
public class IUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findOneByEmail(email);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Email " + email + " not found.");
        }

        return new IUserDetails(user.get());
    }
}
