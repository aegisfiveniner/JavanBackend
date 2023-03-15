package com.maria.taxreportmanagement.tax.repository;

import com.maria.taxreportmanagement.tax.model.TaxReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxReportRepository extends JpaRepository<TaxReport, String> {

    Page<TaxReport> findByStatus(String status, Pageable pageable);
}
