// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.repository;

import com.assetsManagement.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}