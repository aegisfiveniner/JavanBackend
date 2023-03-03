package com.sparepartmanagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "configurations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Configuration
{
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column
    private String configName;
    
    @Column
    private String type;
    
    @Column
    private String details;
    
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
}
