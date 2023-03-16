package com.javanbackend.laporanpajak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javanbackend.laporanpajak.model.LaporanPajak;

@Repository
public interface LaporanPajakRepository extends JpaRepository<LaporanPajak, Long> {
    
}
