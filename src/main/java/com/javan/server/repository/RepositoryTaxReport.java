package com.javan.server.repository;

import com.javan.server.table.TableTaxReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTaxReport extends JpaRepository<TableTaxReport, String> {
}
