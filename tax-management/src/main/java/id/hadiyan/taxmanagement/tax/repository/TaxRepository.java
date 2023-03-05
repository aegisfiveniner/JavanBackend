package id.hadiyan.taxmanagement.tax.repository;

import id.hadiyan.taxmanagement.enums.TaxStatus;
import id.hadiyan.taxmanagement.tax.entities.Tax;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaxRepository extends JpaRepository<Tax, String> {
    Optional<Tax> findByIdAndStatus(String id, TaxStatus status);
    Optional<Tax> findByIdAndStatusIn(String id, List<TaxStatus> status);
    Page<Tax> findAllByUserId(String userId, Pageable pageable);
    Page<Tax> findAllByStatus(TaxStatus status, Pageable pageable);
}
