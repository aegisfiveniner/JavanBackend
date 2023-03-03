// 
// Decompiled by Procyon v0.5.36
// 

package com.sparepartmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparepartmanagement.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}