// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assetsManagement.config.MQConfig;
import com.assetsManagement.entity.Asset;
import com.assetsManagement.entity.AssetConfig;
import com.assetsManagement.entity.AssetSparepart;
import com.assetsManagement.entity.History;
import com.assetsManagement.entity.User;
import com.assetsManagement.entity.model.AssetConfigModel;
import com.assetsManagement.entity.model.AssetModel;
import com.assetsManagement.entity.model.AssetSparepartModel;
import com.assetsManagement.entity.model.ConfigurationModel;
import com.assetsManagement.entity.model.SparepartModel;
import com.assetsManagement.repository.AssetConfigRepository;
import com.assetsManagement.repository.AssetRepository;
import com.assetsManagement.repository.AssetSparepartRepository;
import com.assetsManagement.repository.HistoryRepository;
import com.assetsManagement.repository.UserRepository;
import com.assetsManagement.service.AssetService;
import com.assetsManagement.service.MQService;
import com.google.gson.Gson;

@Service
@Transactional
public class AssetServiceImpl implements AssetService {
	
    private static final String ROLE_ADMIN_ASSET = "ADMIN-ASSET";
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private AssetSparepartRepository assetSparepartRepository;
    @Autowired
    private AssetConfigRepository assetConfigRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private MQService mqService;
    
    @Override
    public AssetModel save(Asset asset) {
        String detail = asset.getId() == null ? "INSERT ASSET" : "UPDATE ASSET";
        asset.setCreatedAt(asset.getCreatedAt() == null ? LocalDateTime.now() : asset.getCreatedAt());
        asset = assetRepository.save(asset);
        AssetModel assetModel = convertToAssetModel(asset);

//        mqService.sendMessage(MQConfig.queueSAsset().getName(), (new Gson()).toJson(assetModel));
//        mqService.sendMessage(MQConfig.queueCAsset().getName(), (new Gson()).toJson(assetModel));
        
        if (!asset.getAssetSpareparts().isEmpty()) {
            if (asset.getId() != null) {
                List<AssetSparepart> assetSparepartList = assetSparepartRepository.findAllByAssetId(asset.getId());
                if (!assetSparepartList.isEmpty()) {
                	List<Integer> assetSparepartIdList = assetSparepartList.stream().map((assetSp) -> {
                        return assetSp.getId();
                    }).collect(Collectors.toList());
                    List<Integer> newAssetSparepartIdList = asset.getAssetSpareparts().stream().map((assetSp) -> {
                        return assetSp.getId();
                    }).collect(Collectors.toList());
                    if (!newAssetSparepartIdList.containsAll(assetSparepartIdList)) {
                    	assetSparepartIdList.stream().forEach((id) -> {
                            if (!newAssetSparepartIdList.contains(id)) {
                                assetSparepartRepository.deleteById(id);
                            }

                        });
                    }
                }
            }

            List<AssetSparepartModel> assetSpareModel = new ArrayList<AssetSparepartModel>();
            for(AssetSparepart assetSparepart : asset.getAssetSpareparts()) {
                assetSparepart.setCreatedAt(assetSparepart.getCreatedAt() == null ? LocalDateTime.now() : assetSparepart.getCreatedAt());
                assetSparepart.setAsset(asset);
                assetSparepartRepository.save(assetSparepart);
                
                AssetSparepartModel assetSparepartModel = convertToAssetSparepartModel(assetSparepart);
                assetSpareModel.add(assetSparepartModel);
            }
            
            assetModel.setAssetSpareparts(assetSpareModel);
        }

        if (!asset.getAssetConfigs().isEmpty()) {
            if (asset.getId() != null) {
                List<AssetConfig> assetConfigList = assetConfigRepository.findAllByAssetId(asset.getId());
                if (!assetConfigList.isEmpty()) {
                    List<Integer> assetConfigIdList = assetConfigList.stream().map((assetConfigx) -> {
                        return assetConfigx.getId();
                    }).collect(Collectors.toList());
                    List<Integer> newAssetConfigIdList = asset.getAssetConfigs().stream().map((assetConfigx) -> {
                        return assetConfigx.getId();
                    }).collect(Collectors.toList());
                    if (!newAssetConfigIdList.containsAll(assetConfigIdList)) {
                        assetConfigIdList.stream().forEach((id) -> {
                            if (!newAssetConfigIdList.contains(id)) {
                                assetConfigRepository.deleteById(id);
                            }

                        });
                    }
                }
            }

            List<AssetConfigModel> assetConfigModelList = new ArrayList<AssetConfigModel>();
            for (AssetConfig assetConfig : asset.getAssetConfigs()) {
                assetConfig.setCreatedAt(assetConfig.getCreatedAt() == null ? LocalDateTime.now() : assetConfig.getCreatedAt());
                assetConfig.setAsset(asset);
                assetConfigRepository.save(assetConfig);
                
                AssetConfigModel assetConfigModel = convertToAssetConfigModel(assetConfig);
                assetConfigModelList.add(assetConfigModel);

//                mqService.sendMessage(MQConfig.queueAssetConfig().getName(), (new Gson()).toJson(assetConfigModel));
            }
            assetModel.setAssetConfigs(assetConfigModelList);
        }

        mqService.sendMessage(MQConfig.queueSAsset().getName(), (new Gson()).toJson(assetModel));
        mqService.sendMessage(MQConfig.queueCAsset().getName(), (new Gson()).toJson(assetModel));
        
        List<User> user = userRepository.findAllByRole(ROLE_ADMIN_ASSET);
        History history = new History();
        history.setAsset(asset);
        history.setCreatedAt(LocalDateTime.now());
        history.setDetails(detail);
        history.setUser(user.isEmpty() ? null : user.get(0));
        historyRepository.save(history);
        return assetModel;
    }

    @Override
    public List<AssetModel> findAll() {
    	List<Asset> assetList = assetRepository.findAll();
    	List<AssetModel> assetModelList = new ArrayList<AssetModel>();
    	for (Asset asset : assetList) {

    		AssetModel assetModel = convertToAssetModel(asset);
    		
    		List<AssetSparepartModel> assetSparepartModelList = new ArrayList<AssetSparepartModel>();
    		for (AssetSparepart assetSparepart : asset.getAssetSpareparts()) {
    			assetSparepartModelList.add(convertToAssetSparepartModel(assetSparepart));
    		}
    		assetModel.setAssetSpareparts(assetSparepartModelList);
    		
    		List<AssetConfigModel> assetConfigModelList = new ArrayList<AssetConfigModel>();
    		for (AssetConfig assetConfig : asset.getAssetConfigs()) {
    			assetConfigModelList.add(convertToAssetConfigModel(assetConfig));
    		}
    		assetModel.setAssetConfigs(assetConfigModelList);
    		assetModelList.add(assetModel);
    	}
        return assetModelList;
    }
    @Override
    public AssetModel findById(Integer id) {
        Optional<Asset> assetOpt = assetRepository.findById(id);
        AssetModel assetModel = new AssetModel();
        if (assetOpt.isPresent()) {
        	Asset asset = assetOpt.get();
    		assetModel = convertToAssetModel(asset);
    		
    		List<AssetSparepartModel> assetSparepartModelList = new ArrayList<AssetSparepartModel>();
    		for (AssetSparepart assetSparepart : asset.getAssetSpareparts()) {
    			assetSparepartModelList.add(convertToAssetSparepartModel(assetSparepart));
    		}
    		assetModel.setAssetSpareparts(assetSparepartModelList);
    		
    		List<AssetConfigModel> assetConfigModelList = new ArrayList<AssetConfigModel>();
    		for (AssetConfig assetConfig : asset.getAssetConfigs()) {
    			assetConfigModelList.add(convertToAssetConfigModel(assetConfig));
    		}
    		assetModel.setAssetConfigs(assetConfigModelList);
        }
        return assetModel;
    }
    
    public AssetSparepartModel convertToAssetSparepartModel(AssetSparepart assetSparepart) {
    	AssetSparepartModel assetSparepartModel = new AssetSparepartModel();
        assetSparepartModel.setId(assetSparepart.getId());
        assetSparepartModel.setCreatedAt(assetSparepart.getCreatedAt());
        assetSparepartModel.setAssetId(assetSparepart.getAsset().getId());
        SparepartModel sparepartModel = new SparepartModel();
        BeanUtils.copyProperties(assetSparepart.getSparepart(), sparepartModel);
        assetSparepartModel.setSparepart(sparepartModel);
        return assetSparepartModel;
    }
    public AssetConfigModel convertToAssetConfigModel(AssetConfig assetConfig) {
    	AssetConfigModel assetConfigModel = new AssetConfigModel();
        assetConfigModel.setId(assetConfig.getId());
        assetConfigModel.setCreatedAt(assetConfig.getCreatedAt());
        assetConfigModel.setAssetId(assetConfig.getAsset().getId());
        ConfigurationModel configurationModel = new ConfigurationModel();
        BeanUtils.copyProperties(assetConfig.getConfiguration(), configurationModel);
        assetConfigModel.setConfiguration(configurationModel);
        
        return assetConfigModel;
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