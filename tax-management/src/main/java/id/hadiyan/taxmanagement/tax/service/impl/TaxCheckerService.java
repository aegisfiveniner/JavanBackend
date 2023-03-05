package id.hadiyan.taxmanagement.tax.service.impl;

import id.hadiyan.taxmanagement.enums.Role;
import id.hadiyan.taxmanagement.enums.TaxStatus;
import id.hadiyan.taxmanagement.tax.entities.Tax;
import id.hadiyan.taxmanagement.tax.repository.TaxRepository;
import id.hadiyan.taxmanagement.tax.service.TaxRoleStartegy;
import id.hadiyan.taxmanagement.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxCheckerService implements TaxRoleStartegy {
    private final TaxRepository taxRepository;

    @Override
    public boolean hasRole(String role) {
        return Role.CHECKER.name().equalsIgnoreCase(role);
    }

    @Override
    public Page<Tax> findAll(User user, Pageable pageable) {
        return taxRepository.findAllByStatus(TaxStatus.SUBMITTED, pageable);
    }
}
