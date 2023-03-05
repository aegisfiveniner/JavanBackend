package com.management.taxreport.repository;

import com.management.taxreport.entity.TaxReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxReportRepository extends JpaRepository<TaxReport, Long> {

    Optional<TaxReport> findByReceipt(String receipt);
}
