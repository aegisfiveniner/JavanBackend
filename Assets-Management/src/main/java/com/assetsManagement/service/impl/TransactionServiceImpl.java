// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assetsManagement.config.MQConfig;
import com.assetsManagement.entity.Asset;
import com.assetsManagement.entity.Transaction;
import com.assetsManagement.entity.model.AssetModel;
import com.assetsManagement.entity.model.SparepartModel;
import com.assetsManagement.entity.model.TransactionModel;
import com.assetsManagement.repository.TransactionRepository;
import com.assetsManagement.service.MQService;
import com.assetsManagement.service.TransactionService;
import com.google.gson.Gson;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private MQService mqService;

    public TransactionModel save(Transaction transaction) {
        transaction.setCreatedAt(transaction.getCreatedAt() == null ? LocalDateTime.now() : transaction.getCreatedAt());
        transaction = transactionRepository.save(transaction);
        mqService.sendMessage(MQConfig.queueTransaction().getName(), (new Gson()).toJson(transaction));
        return convertToModel(transaction);
    }

    public List<TransactionModel> findAll() {
    	List<Transaction> transactionList = transactionRepository.findAll();
    	List<TransactionModel> modelList = new ArrayList<TransactionModel>();
    	for (Transaction transaction : transactionList) {
    		modelList.add(convertToModel(transaction));
    	}
        return modelList;
    }

    public TransactionModel findById(Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.isPresent() ? convertToModel(transaction.get()) : null;
    }
    
    public TransactionModel convertToModel(Transaction transaction) {
    	SparepartModel sparepart = new SparepartModel();
    	BeanUtils.copyProperties(transaction.getSparepart(), sparepart);
    	return TransactionModel.builder()
    		.id(transaction.getId())
    		.createdAt(transaction.getCreatedAt())
    		.asset(convertToAssetModel(transaction.getAsset()))
    		.sparepart(sparepart)
    		.type(transaction.getType())
    		.startTime(transaction.getStartTime())
    		.endTime(transaction.getEndTime())
    		.build();
    }
    public AssetModel convertToAssetModel(Asset asset) {
        AssetModel assetModel = new AssetModel();
        assetModel.setId(asset.getId());
        assetModel.setCreatedAt(asset.getCreatedAt());
        assetModel.setAssetName(asset.getAssetName());
        assetModel.setCurrentStatus(asset.getCurrentStatus());
        assetModel.setLocation(asset.getLocation());
        assetModel.setManufacturer(asset.getManufacturer());
        assetModel.setModelNumber(asset.getModelNumber());
        assetModel.setSerialNumber(asset.getSerialNumber());
        assetModel.setType(asset.getType());
        
        return assetModel;
    }
}