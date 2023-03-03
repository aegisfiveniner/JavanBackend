package com.sparepartmanagement.entity;

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
@Table(name = "spareparts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sparepart extends BaseEntity {

    @Column
    private String sparepartName;
    
    @Column
    private Integer quantity;
    
    @Column
    private String type;
    
    @ManyToOne
    @JoinColumn(
            name = "locationId"
    )
    private Location location;
    
    @Column
    private String modelNumber;
    
    @Column
    private String manufacture;
    
    @Column
    private String currentStatus;
}
