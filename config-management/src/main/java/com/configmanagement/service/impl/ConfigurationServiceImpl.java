package com.configmanagement.service.impl;

import com.configmanagement.config.MQConfig;
import com.configmanagement.entity.Configuration;
import com.configmanagement.entity.History;
import com.configmanagement.entity.User;
import com.configmanagement.repository.ConfigurationRepository;
import com.configmanagement.repository.HistoryRepository;
import com.configmanagement.repository.UserRepository;
import com.configmanagement.service.ConfigurationService;
import com.configmanagement.service.MQService;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {
    private static final String ROLE_ADMIN_CONFIG = "ADMIN-CONFIG";
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private MQService mqService;

    public ConfigurationServiceImpl() {
    }

    public Configuration save(Configuration config) {
        String detail = config.getId() == null ? "INSERT CONFIGURATION" : "UPDATE CONFIGURATION";
        config.setCreatedAt(config.getCreatedAt() == null ? LocalDateTime.now() : config.getCreatedAt());
        config = configurationRepository.save(config);
        
        mqService.sendMessage(MQConfig.queueConfig().getName(), (new Gson()).toJson(config));
        
        List<User> userList = userRepository.findAllByRole(ROLE_ADMIN_CONFIG);
        History history = new History();
        history.setConfiguration(config);
        history.setCreatedAt(LocalDateTime.now());
        history.setDetails(detail);
        history.setUser(!userList.isEmpty() ? (User)userList.get(0) : null);
        history = historyRepository.save(history);
        mqService.sendMessage(MQConfig.queueHistory().getName(), (new Gson()).toJson(history));
        return config;
    }

    public List<Configuration> findAll() {
        return configurationRepository.findAll();
    }

    public Configuration findById(Integer id) {
        Optional<Configuration> configuration = configurationRepository.findById(id);
        return configuration.isPresent() ? configuration.get() : null;
    }
}