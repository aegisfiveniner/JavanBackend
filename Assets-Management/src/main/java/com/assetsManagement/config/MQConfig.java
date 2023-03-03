package com.assetsManagement.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig
{
    public static final String MQ_UPDATE_SPAREPART = "MQ-UPDATE-SPAREPART";
    public static final String MQ_UPDATE_CONFIG = "MQ-UPDATE-CONFIG";
    public static final String MQ_S_UPDATE_ASSET = "MQ-S-UPDATE-ASSET";
    public static final String MQ_C_UPDATE_ASSET = "MQ-C-UPDATE-ASSET";
    public static final String MQ_S_UPDATE_LOCATION = "MQ-S-UPDATE-LOCATION";
    public static final String MQ_C_UPDATE_LOCATION = "MQ-C-UPDATE-LOCATION";
    public static final String MQ_S_UPDATE_USER = "MQ-S-UPDATE-USER";
    public static final String MQ_C_UPDATE_USER = "MQ-C-UPDATE-USER";
    public static final String MQ_UPDATE_TRANSACTION = "MQ-UPDATE-TRANSACTION";
    public static final String MQ_C_UPDATE_HISTORY = "MQ-C-UPDATE-HISTORY";
    public static final String MQ_S_UPDATE_HISTORY = "MQ-S-UPDATE-HISTORY";
    
    @Bean
    public static Queue queueSLocation() {
        return new Queue(MQ_S_UPDATE_LOCATION);
    }
    
    @Bean
    public static Queue queueCLocation() {
        return new Queue(MQ_C_UPDATE_LOCATION);
    }
    
    @Bean
    public static Queue queueSUser() {
        return new Queue(MQ_S_UPDATE_USER);
    }
    
    @Bean
    public static Queue queueCUser() {
        return new Queue(MQ_C_UPDATE_USER);
    }
    
    @Bean
    public static Queue queueConfig() {
        return new Queue(MQ_UPDATE_CONFIG);
    }
    
    @Bean
    public static Queue queueSAsset() {
        return new Queue(MQ_S_UPDATE_ASSET);
    }
    
    @Bean
    public static Queue queueCAsset() {
        return new Queue(MQ_C_UPDATE_ASSET);
    }
    
    @Bean
    public static Queue queueSparepart() {
        return new Queue(MQ_UPDATE_SPAREPART);
    }
    
    @Bean
    public static Queue queueTransaction() {
        return new Queue(MQ_UPDATE_TRANSACTION);
    }
    
    @Bean
    public static Queue queueSHistory() {
        return new Queue(MQ_S_UPDATE_HISTORY);
    }
    
    @Bean
    public static Queue queueCHistory() {
        return new Queue(MQ_C_UPDATE_HISTORY);
    }
}
