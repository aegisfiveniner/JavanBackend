package com.configmanagement.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    public static final String MQ_UPDATE_CONFIG = "MQ-UPDATE-CONFIG";
    public static final String MQ_C_UPDATE_LOCATION = "MQ-C-UPDATE-LOCATION";
    public static final String MQ_C_UPDATE_ASSET = "MQ-C-UPDATE-ASSET";
    public static final String MQ_C_UPDATE_USER = "MQ-C-UPDATE-USER";
    public static final String MQ_C_UPDATE_HISTORY = "MQ-C-UPDATE-HISTORY";
    public static final String MQ_UPDATE_ASSET_CONFIG = "MQ-UPDATE-ASSET-CONFIG";

    @Bean
    public static Queue queueAssetConfig() {
        return new Queue(MQ_UPDATE_ASSET_CONFIG);
    }

    @Bean
    public static Queue queueAsset() {
        return new Queue(MQ_C_UPDATE_ASSET);
    }

    @Bean
    public static Queue queueLocation() {
        return new Queue(MQ_C_UPDATE_LOCATION);
    }

    @Bean
    public static Queue queueUser() {
        return new Queue(MQ_C_UPDATE_USER);
    }

    @Bean
    public static Queue queueConfig() {
        return new Queue(MQ_UPDATE_CONFIG);
    }

    @Bean
    public static Queue queueHistory() {
        return new Queue(MQ_C_UPDATE_HISTORY);
    }
}