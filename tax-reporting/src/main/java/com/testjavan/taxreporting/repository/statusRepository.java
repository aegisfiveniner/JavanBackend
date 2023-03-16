package com.testjavan.taxreporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testjavan.taxreporting.models.status;

public interface statusRepository extends JpaRepository<status, Integer> {
    @Query(value = "SELECT * FROM status " +
            "WHERE statusid = ?1", nativeQuery = true)
    status findStatus(Integer statusid);
}
