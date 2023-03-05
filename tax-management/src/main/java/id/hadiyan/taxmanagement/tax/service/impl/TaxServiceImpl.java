package id.hadiyan.taxmanagement.tax.service.impl;

import id.hadiyan.taxmanagement.enums.TaxStatus;
import id.hadiyan.taxmanagement.tax.entities.Tax;
import id.hadiyan.taxmanagement.tax.repository.TaxRepository;
import id.hadiyan.taxmanagement.tax.service.TaxRoleStartegy;
import id.hadiyan.taxmanagement.tax.service.TaxService;
import id.hadiyan.taxmanagement.user.entities.User;
import id.hadiyan.taxmanagement.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {
    private final UserRepository userRepository;
    private final TaxRepository taxRepository;
    private final List<TaxRoleStartegy> taxRoleStartegies;

    @Override
    public Page<Tax> findAll(Pageable pageable) {
        String username = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSubject();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return taxRoleStartegies.stream()
                .filter(taxRoleStartegy -> taxRoleStartegy.hasRole(user.getRole().name()))
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("Access Denied"))
                .findAll(user, pageable);
    }

    @Override
    public void submit() {
        String username = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSubject();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Tax tax = Tax.builder()
                .status(TaxStatus.SUBMITTED)
                .userId(user.getId())
                .userName(user.getName())
                .username(user.getUsername())
                .receiptNumber(String.format("RECEIPT-%s-%d",username.toUpperCase(),System.currentTimeMillis()))
                .createdAt(LocalDateTime.now())
                .createdBy(user.getUsername())
                .build();
        taxRepository.save(tax);
    }

    @Override
    public void check(String id) {
        Tax tax = taxRepository.findByIdAndStatus(id, TaxStatus.SUBMITTED).orElseThrow(() -> new EntityNotFoundException("Tax not found"));
        String username = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSubject();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        tax.setStatus(TaxStatus.CHECKED);
        tax.setCheckedAt(LocalDateTime.now());
        tax.setCheckedBy(user.getUsername());
        taxRepository.save(tax);
    }

    @Override
    public void approve(String id) {
        Tax tax = taxRepository.findByIdAndStatus(id, TaxStatus.CHECKED).orElseThrow(() -> new EntityNotFoundException("Tax not found"));
        String username = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSubject();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        tax.setStatus(TaxStatus.APPROVED);
        tax.setApprovedAt(LocalDateTime.now());
        tax.setApprovedBy(user.getUsername());
        taxRepository.save(tax);
    }

    @Override
    public void reject(String id) {
        Tax tax = taxRepository.findByIdAndStatusIn(id, List.of(TaxStatus.SUBMITTED, TaxStatus.CHECKED)).orElseThrow(() -> new EntityNotFoundException("Tax not found"));
        String username = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSubject();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        tax.setStatus(TaxStatus.REJECTED);
        tax.setRe(LocalDateTime.now());
        tax.setApprovedBy(user.getUsername());
        taxRepository.save(tax);
    }
}
