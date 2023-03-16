package com.maria.taxreportmanagement.seeder;

import com.maria.taxreportmanagement.user.model.User;
import com.maria.taxreportmanagement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class Seeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUserTable();
    }

    private void seedUserTable() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            User newUser = new User();
            newUser.setId(UUID.randomUUID().toString());
            newUser.setEmail("admin@admin.com");
            newUser.setPassword(passwordEncoder.encode("12345"));
            newUser.setRole("ADMIN");
            userRepository.save(newUser);
        }
    }
}
