package com.assetsManagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "id"
    )
    private Integer id;
    @CreatedDate
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @Column(
            updatable = false
    )
    private LocalDateTime createdAt;
}
