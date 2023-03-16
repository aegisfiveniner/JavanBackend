package com.testjavan.taxreporting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testjavan.taxreporting.models.taxReport;

public interface taxReportRepository extends JpaRepository<taxReport, Integer> {
    @Query(value = "SELECT * FROM taxReport t" +
    "INNER JOIN status s ON t.statusid = s.statusid" +
    "WHERE statusid = 3", nativeQuery = true)
    List<taxReport> findStatus();

    @Query(value = "SELECT * FROM taxReport" +
    "WHERE statusid = ?1", nativeQuery = true)
    taxReport findStatusbyName(Integer statusid);
}
