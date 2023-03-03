// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetsManagement.entity.Sparepart;

@Repository
public interface SparepartRepository extends JpaRepository<Sparepart, Integer> {
}