// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.service;

import com.assetsManagement.entity.Transaction;
import com.assetsManagement.entity.model.TransactionModel;

import java.util.List;

public interface TransactionService {
	TransactionModel save(Transaction transaction);

    List<TransactionModel> findAll();

    TransactionModel findById(Integer id);
}