package com.javanbackend.laporanpajak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javanbackend.laporanpajak.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    
}
