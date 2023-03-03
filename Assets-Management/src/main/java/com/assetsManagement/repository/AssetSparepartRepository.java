// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.repository;

import com.assetsManagement.entity.AssetSparepart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetSparepartRepository extends JpaRepository<AssetSparepart, Integer> {
    List<AssetSparepart> findAllByAssetId(Integer assetId);
}
