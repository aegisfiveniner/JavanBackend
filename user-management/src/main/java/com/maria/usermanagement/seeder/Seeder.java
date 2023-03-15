package com.maria.usermanagement.seeder;

import com.maria.usermanagement.enumerator.EnumRole;
import com.maria.usermanagement.user.model.User;
import com.maria.usermanagement.user.repository.UserRepository;
import com.maria.usermanagement.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Seeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordUtil passwordUtil;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUserTable();
    }

    private void seedUserTable() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            User newUser = new User();
            newUser.setEmail("admin@admin.com");
            newUser.setPassword(passwordUtil.encodePassword("12345"));
            newUser.setRole(EnumRole.ADMIN.getValue());
            userRepository.save(newUser);
        }
    }
}
