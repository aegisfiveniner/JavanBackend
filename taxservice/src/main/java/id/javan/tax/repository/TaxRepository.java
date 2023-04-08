package id.javan.tax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import id.javan.tax.entity.Tax;
import id.javan.tax.entity.TaxStatusEnum;

public interface TaxRepository extends JpaRepository<Tax, Long> {
  List<Tax> findByStatus(TaxStatusEnum status);
}