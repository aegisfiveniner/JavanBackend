package com.assetsManagement.entity.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetConfigModel implements Serializable  {
	
	private Integer Id;
	private LocalDateTime createdAt;
	private Integer assetId;
	private ConfigurationModel configuration;

}
