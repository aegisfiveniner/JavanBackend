// 
// Decompiled by Procyon v0.5.36
// 

package com.configmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.configmanagement.entity.AssetConfig;

@Repository
public interface AssetConfigRepository extends JpaRepository<AssetConfig, Integer> {
    List<AssetConfig> findAllByAssetId(Integer assetId);
    void deleteAllByAssetId(Integer AssetId);
}
