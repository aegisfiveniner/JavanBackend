package com.assetsManagement.entity.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationModel {

    private Integer id;
    private String configName;
    private String type;
    private String details;
    private LocalDateTime createdAt;
    
}
