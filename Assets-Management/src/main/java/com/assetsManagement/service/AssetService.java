// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.service;

import com.assetsManagement.entity.Asset;
import com.assetsManagement.entity.model.AssetModel;

import java.util.List;

public interface AssetService {
    AssetModel save(Asset asset);

    List<AssetModel> findAll();

    AssetModel findById(Integer id);
}