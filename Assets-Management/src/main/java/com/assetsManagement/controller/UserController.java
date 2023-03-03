// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.controller;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assetsManagement.entity.User;
import com.assetsManagement.service.UserService;

@RestController
@Transactional(readOnly = true)
@RequestMapping({ "/user" })
public class UserController
{
    @Autowired
    private UserService userService;
    
    @PostMapping
    private ResponseEntity<User> registerUser(@RequestBody final User user) {
        user.setPassword(user.getPassword() == null ? String.valueOf(new Random().nextInt(1000000000)) : user.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}
