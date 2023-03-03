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
public class TransactionModel {
		
	private Integer id;
	private LocalDateTime createdAt;
    private AssetModel asset;
    private SparepartModel sparepart;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
