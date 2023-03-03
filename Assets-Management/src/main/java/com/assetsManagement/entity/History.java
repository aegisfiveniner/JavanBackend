package com.assetsManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "history")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class History extends BaseEntity
{
    
	@ManyToOne
    @JoinColumn(name = "assetId")
    private Asset asset;
    
    @ManyToOne
    @JoinColumn(name = "configId")
    private Configuration configuration;
    
    @ManyToOne
    @JoinColumn(name = "sparepartId")
    private Sparepart sparepart;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    
    @Column
    private String details;
}
