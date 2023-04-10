package com.alurkerja.seeder;

import com.alurkerja.crud.user.ERole;
import com.alurkerja.crud.user.User;
import com.alurkerja.crud.user.UserRepository;
import com.alurkerja.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRegistrationSeeder {
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
            newUser.setEmail("admin@javan.co.id");
            newUser.setPassword(passwordUtil.encodePassword("asdf1234"));
            newUser.setRole(ERole.ADMIN.getValue());
            userRepository.save(newUser);
        }
    }
}
