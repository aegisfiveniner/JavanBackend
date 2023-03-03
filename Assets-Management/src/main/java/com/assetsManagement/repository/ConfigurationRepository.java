// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.repository;

import com.assetsManagement.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
}
