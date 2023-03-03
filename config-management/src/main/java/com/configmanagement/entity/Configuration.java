package com.configmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
public class Configuration extends BaseEntity
{
    @Column
    private String configName;
    @Column
    private String type;
    @Column
    private String details;
}
