package com.sparepartmanagement.controller;

import com.assetsManagement.entity.model.AssetModel;
import com.assetsManagement.entity.model.AssetSparepartModel;
import com.google.gson.Gson;
import com.sparepartmanagement.config.MQConfig;
import com.sparepartmanagement.entity.Asset;
import com.sparepartmanagement.entity.AssetSparepart;
import com.sparepartmanagement.entity.Location;
import com.sparepartmanagement.entity.Sparepart;
import com.sparepartmanagement.entity.Transaction;
import com.sparepartmanagement.entity.User;
import com.sparepartmanagement.repository.AssetRepository;
import com.sparepartmanagement.repository.AssetSparepartRepository;
import com.sparepartmanagement.repository.LocationRepository;
import com.sparepartmanagement.repository.TransactionRepository;
import com.sparepartmanagement.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SparepartRabbitMqController {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AssetSparepartRepository assetSparepartRepository;

    public SparepartRabbitMqController() {
    }

    @RabbitListener(queues = {MQConfig.MQ_S_UPDATE_LOCATION})
    public void receiveLocation(@Payload String location) {
        Location newLocation = new Gson().fromJson(location, Location.class);
        locationRepository.save(newLocation);
    }

    @RabbitListener(queues = {MQConfig.MQ_S_UPDATE_USER})
    public void receiveUser(@Payload String user) {
        User newUser = new Gson().fromJson(user, User.class);
        userRepository.save(newUser);
    }

    @RabbitListener(queues = {MQConfig.MQ_UPDATE_TRANSACTION})
    public void receiveTransaction(@Payload String transaction) {
        Transaction newTransaction = new Gson().fromJson(transaction, Transaction.class);
        transactionRepository.save(newTransaction);
    }

    @RabbitListener(queues = {MQConfig.MQ_S_UPDATE_ASSET})
    public void receiveAsset(@Payload String asset) {
    	AssetModel assetModel = new Gson().fromJson(asset, AssetModel.class);
        assetSparepartRepository.deleteAllByAssetId(assetModel.getId());
        Asset newAsset = assetRepository.save(convertToAsset(assetModel));
        for (AssetSparepartModel model : assetModel.getAssetSpareparts()) {
        	assetSparepartRepository.save(convertToAssetSparepart(newAsset, model));
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
    
    public AssetSparepart convertToAssetSparepart(Asset asset, AssetSparepartModel assetSparepartModel) {
    	Sparepart sparepart = new Sparepart();
    	BeanUtils.copyProperties(assetSparepartModel.getSparepart(), sparepart);
    	return AssetSparepart.builder()
    		.id(assetSparepartModel.getId())
    		.createdAt(assetSparepartModel.getCreatedAt())
    		.sparepart(sparepart)
    		.asset(asset)
    		.build();
    }
//    @RabbitListener(queues = {MQConfig.MQ_UPDATE_ASSET_SPAREPART})
//    public void receiveAssetSparepart(@Payload String assetSparepart) {
//        assetSparepartRepository.save(new Gson().fromJson(assetSparepart, AssetSparepart.class));
//    }
}