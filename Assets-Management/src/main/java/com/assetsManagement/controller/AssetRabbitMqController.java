// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.assetsManagement.config.MQConfig;
import com.assetsManagement.entity.Configuration;
import com.assetsManagement.entity.History;
import com.assetsManagement.entity.Sparepart;
import com.assetsManagement.repository.ConfigurationRepository;
import com.assetsManagement.repository.HistoryRepository;
import com.assetsManagement.repository.SparepartRepository;
import com.google.gson.Gson;

@Component
public class AssetRabbitMqController
{
    @Autowired
    private SparepartRepository sparepartRepository;
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private HistoryRepository historyRepository;
    
    @RabbitListener(queues = {MQConfig.MQ_UPDATE_SPAREPART})
    public void receiveSparepart(@Payload final String sparepart) {
        System.out.println("Received message : " + sparepart);
        Sparepart newSparepart = new Gson().fromJson(sparepart, Sparepart.class);
        sparepartRepository.save(newSparepart);
    }
    
    @RabbitListener(queues = {MQConfig.MQ_UPDATE_CONFIG})
    public void receiveConfig(@Payload final String configuration) {
        System.out.println("Received message : " + configuration);
        Configuration newConfiguration = new Gson().fromJson(configuration, Configuration.class);
        configurationRepository.save(newConfiguration);
    }
    
    @RabbitListener(queues = {MQConfig.MQ_S_UPDATE_HISTORY})
    public void receiveHistorySparepart(@Payload final String history) {
        System.out.println("Received message : " + history);
        History newHistory = new Gson().fromJson(history, History.class);
        this.historyRepository.save(newHistory);
    }
    
    @RabbitListener(queues = {MQConfig.MQ_C_UPDATE_HISTORY})
    public void receiveHistoryConfig(@Payload final String history) {
        System.out.println("Received message : " + history);
        History newHistory = new Gson().fromJson(history, History.class);
        historyRepository.save(newHistory);
    }
}
