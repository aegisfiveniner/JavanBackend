package com.assetsManagement.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "assetConfigs"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetConfig extends BaseEntity {
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assetId")
    private Asset asset;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configId")
    private Configuration configuration;
}
