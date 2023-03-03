package com.configmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Asset
{
    @Id
    @Column(name = "id")
    private Integer id;
    
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
    
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
    private List<AssetConfig> assetConfigs;
}