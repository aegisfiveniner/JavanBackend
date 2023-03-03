package com.configmanagement.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.assetsManagement.entity.model.AssetConfigModel;
import com.assetsManagement.entity.model.AssetModel;
import com.configmanagement.config.MQConfig;
import com.configmanagement.entity.Asset;
import com.configmanagement.entity.AssetConfig;
import com.configmanagement.entity.Configuration;
import com.configmanagement.entity.Location;
import com.configmanagement.entity.User;
import com.configmanagement.repository.AssetConfigRepository;
import com.configmanagement.repository.AssetRepository;
import com.configmanagement.repository.LocationRepository;
import com.configmanagement.repository.UserRepository;
import com.google.gson.Gson;

@Component
public class ConfigurationRabbitMqController {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private AssetConfigRepository assetConfigRepository;

    public ConfigurationRabbitMqController() {
    }

    @RabbitListener(queues = {MQConfig.MQ_C_UPDATE_LOCATION})
    public void receiveLocation(@Payload String location) {
        Location newLocation = new Gson().fromJson(location, Location.class);
        locationRepository.save(newLocation);
    }

    @RabbitListener(queues = {MQConfig.MQ_C_UPDATE_USER})
    public void receiveUser(@Payload String user) {
        User newUser = new Gson().fromJson(user, User.class);
        userRepository.save(newUser);
    }

    @RabbitListener(queues = {MQConfig.MQ_C_UPDATE_ASSET})
    public void receiveAsset(@Payload String asset) {
    	AssetModel assetModel = new Gson().fromJson(asset, AssetModel.class);
        assetConfigRepository.deleteAllByAssetId(assetModel.getId());
        Asset newAsset = assetRepository.save(convertToAsset(assetModel));
        for (AssetConfigModel model : assetModel.getAssetConfigs()) {
        	assetConfigRepository.save(convertToAssetConfig(newAsset, model));
        }
    }
    
    public Asset convertToAsset(AssetModel assetModel) {
    	return Asset.builder()
        	.id(assetModel.getId())
        	.createdAt(assetModel.getCreatedAt())
        	.assetName(assetModel.getAssetName())
        	.serialNumber(assetModel.getSerialNumber())
        	.type(assetModel.getType())
        	.location(assetModel.getLocation())
        	.modelNumber(assetModel.getModelNumber())
        	.manufacturer(assetModel.getManufacturer())
        	.currentStatus(assetModel.getCurrentStatus())
        	.build();
    	
    }
    
    public AssetConfig convertToAssetConfig(Asset asset, AssetConfigModel assetConfigModel) {
    	Configuration configuration = new Configuration();
    	BeanUtils.copyProperties(assetConfigModel.getConfiguration(), configuration);
    	return AssetConfig.builder()
    		.id(assetConfigModel.getId())
    		.createdAt(assetConfigModel.getCreatedAt())
    		.configuration(configuration)
    		.asset(asset)
    		.build();
    }

//    @RabbitListener(queues = {MQConfig.MQ_UPDATE_ASSET_CONFIG})
//    public void receiveAssetConfig(@Payload String assetConfig) {
//        assetConfigRepository.save(new Gson().fromJson(assetConfig, AssetConfig.class));
//    }
}