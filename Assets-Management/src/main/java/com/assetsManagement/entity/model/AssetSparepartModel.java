package com.assetsManagement.entity.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetSparepartModel {

	private Integer Id;
	private LocalDateTime createdAt;
	private Integer assetId;
	private SparepartModel sparepart;
}
