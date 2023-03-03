// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.assetsManagement.entity.Transaction;
import com.assetsManagement.entity.model.TransactionModel;

import org.springframework.beans.factory.annotation.Autowired;
import com.assetsManagement.service.TransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(readOnly = true)
@RequestMapping({ "/transaction" })
public class TransactionController
{
    @Autowired
    private TransactionService transactionService;
    
    @PostMapping
    private ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
    	TransactionModel transactionModel = transactionService.save(transaction);
        return ResponseEntity.ok(transactionModel);
    }
    
    @GetMapping
    private ResponseEntity<?> getTransactionList() {
        List<TransactionModel> transactionList = transactionService.findAll();
        return ResponseEntity.ok(transactionList);
    }
    
    @GetMapping({ "/detail/{id}" })
    private ResponseEntity<?> getTransaction(@PathVariable final Integer id) {
    	TransactionModel transaction = transactionService.findById(id);
        return ResponseEntity.ok(transaction);
    }
}
