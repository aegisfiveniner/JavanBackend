package com.assetsManagement.entity.model;

import java.time.LocalDateTime;
import java.util.List;

import com.assetsManagement.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetModel {

    private Integer id;
    private LocalDateTime createdAt;
    private String assetName;
    private String serialNumber;
    private String type;
    private Location location;
    private String modelNumber;
    private String manufacturer;
    private String currentStatus;
    private List<AssetSparepartModel> assetSpareparts;
    private List<AssetConfigModel> assetConfigs;
    
}
