package com.assetsManagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Sparepart {
    @Id
    @Column(name = "id")
    private Integer id;
    
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
    
    @Column
    private String sparepartName;
    
    @Column
    private Integer quantity;
    
    @Column
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    
    @Column
    private String modelNumber;
    
    @Column
    private String manufacture;
    
    @Column
    private String currentStatus;
}
