// 
// Decompiled by Procyon v0.5.36
// 

package com.sparepartmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparepartmanagement.entity.AssetSparepart;

@Repository
public interface AssetSparepartRepository extends JpaRepository<AssetSparepart, Integer> {
    List<AssetSparepart> findAllByAssetId(Integer assetId);
    void deleteAllByAssetId(Integer assetId);
}
