// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.service.impl;

import com.assetsManagement.config.MQConfig;
import com.assetsManagement.entity.User;
import com.assetsManagement.repository.UserRepository;
import com.assetsManagement.service.MQService;
import com.assetsManagement.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MQService mqService;

    public UserServiceImpl() {
    }

    public User save(User user) {
        user = userRepository.save(user);
        mqService.sendMessage(MQConfig.queueSUser().getName(), (new Gson()).toJson(user));
        mqService.sendMessage(MQConfig.queueCUser().getName(), (new Gson()).toJson(user));
        return user;
    }
}