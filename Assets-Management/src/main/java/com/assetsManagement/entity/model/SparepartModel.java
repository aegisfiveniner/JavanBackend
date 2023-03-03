package com.assetsManagement.entity.model;

import java.time.LocalDateTime;

import com.assetsManagement.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SparepartModel {

    private Integer id;
    private LocalDateTime createdAt;
    private String sparepartName;
    private Integer quantity;
    private String type;
    private Location location;
    private String modelNumber;
    private String manufacture;
    private String currentStatus;
    
}
