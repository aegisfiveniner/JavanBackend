package com.configmanagement.service;

import com.configmanagement.entity.Configuration;
import java.util.List;

public interface ConfigurationService {
    Configuration save(Configuration config);

    List<Configuration> findAll();

    Configuration findById(Integer id);
}