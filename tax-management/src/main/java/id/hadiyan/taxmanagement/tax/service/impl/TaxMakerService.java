package id.hadiyan.taxmanagement.tax.service.impl;

import id.hadiyan.taxmanagement.enums.Role;
import id.hadiyan.taxmanagement.tax.entities.Tax;
import id.hadiyan.taxmanagement.tax.repository.TaxRepository;
import id.hadiyan.taxmanagement.tax.service.TaxRoleStartegy;
import id.hadiyan.taxmanagement.user.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaxMakerService implements TaxRoleStartegy {
    private final TaxRepository taxRepository;

    @Override
    public boolean hasRole(String role) {
        return Role.MAKER.name().equalsIgnoreCase(role);
    }

    @Override
    public Page<Tax> findAll(User user, Pageable pageable) {
        log.info("user id is : {}", user.getId());
        return taxRepository.findAllByUserId(user.getId(), pageable);
    }
}
