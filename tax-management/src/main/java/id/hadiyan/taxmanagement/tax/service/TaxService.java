package id.hadiyan.taxmanagement.tax.service;

import id.hadiyan.taxmanagement.tax.entities.Tax;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaxService {
    Page<Tax> findAll(Pageable pageable);
    void submit();
    void check(String id);
    void approve(String id);
    void reject(String id);
}
