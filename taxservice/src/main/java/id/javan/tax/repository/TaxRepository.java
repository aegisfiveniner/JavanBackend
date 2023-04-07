package id.javan.tax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.javan.tax.entity.User;

public interface TaxRepository extends JpaRepository<User, Long> {
}