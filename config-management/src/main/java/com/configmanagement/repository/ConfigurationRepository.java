// 
// Decompiled by Procyon v0.5.36
// 

package com.configmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.configmanagement.entity.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
}
