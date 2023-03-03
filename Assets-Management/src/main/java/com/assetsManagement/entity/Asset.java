package com.assetsManagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset extends BaseEntity
{
    @Column
    private String assetName;
    @Column
    private String serialNumber;
    @Column
    private String type;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    @Column
    private String modelNumber;
    @Column
    private String manufacturer;
    @Column
    private String currentStatus;
    @OneToMany(mappedBy = "asset", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<AssetSparepart> assetSpareparts;
    @OneToMany(mappedBy = "asset", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<AssetConfig> assetConfigs;
}