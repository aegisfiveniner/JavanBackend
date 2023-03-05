package id.hadiyan.taxmanagement.tax.service;

import id.hadiyan.taxmanagement.tax.entities.Tax;
import id.hadiyan.taxmanagement.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaxRoleStartegy {
    boolean hasRole(String role);
    Page<Tax> findAll(User user, Pageable pageable);
}
