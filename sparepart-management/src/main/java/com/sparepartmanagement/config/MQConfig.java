package com.sparepartmanagement.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    public static final String MQ_UPDATE_SPAREPART = "MQ-UPDATE-SPAREPART";
    public static final String MQ_S_UPDATE_ASSET = "MQ-S-UPDATE-ASSET";
    public static final String MQ_S_UPDATE_LOCATION = "MQ-S-UPDATE-LOCATION";
    public static final String MQ_S_UPDATE_USER = "MQ-S-UPDATE-USER";
    public static final String MQ_UPDATE_TRANSACTION = "MQ-UPDATE-TRANSACTION";
    public static final String MQ_S_UPDATE_HISTORY = "MQ-S-UPDATE-HISTORY";
    public static final String MQ_UPDATE_ASSET_SPAREPART = "MQ-UPDATE-ASSET-SPAREPART";

    @Bean
    public static Queue queueAssetSparepart() {
        return new Queue(MQ_UPDATE_ASSET_SPAREPART);
    }

    @Bean
    public static Queue queueLocation() {
        return new Queue(MQ_S_UPDATE_LOCATION);
    }

    @Bean
    public static Queue queueAsset() {
        return new Queue(MQ_S_UPDATE_ASSET);
    }

    @Bean
    public static Queue queueUser() {
        return new Queue(MQ_S_UPDATE_USER);
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
    public static Queue queueHistory() {
        return new Queue(MQ_S_UPDATE_HISTORY);
    }

}