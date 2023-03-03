package com.configmanagement.controller;

import com.configmanagement.entity.Configuration;
import com.configmanagement.service.ConfigurationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(
        readOnly = true
)
@RequestMapping({"/config"})
public class ConfigurationController {
    @Autowired
    private ConfigurationService configurationService;

    public ConfigurationController() {
    }

    @PostMapping
    private ResponseEntity<?> saveConfig(@RequestBody Configuration config) {
        config = configurationService.save(config);
        return ResponseEntity.ok(config);
    }

    @GetMapping
    private ResponseEntity<?> getConfigList() {
        List<Configuration> configList = configurationService.findAll();
        return ResponseEntity.ok(configList);
    }

    @GetMapping({"/detail/{id}"})
    private ResponseEntity<?> getConfig(@PathVariable Integer id) {
        Configuration config = configurationService.findById(id);
        return ResponseEntity.ok(config != null ? config : null);
    }
}