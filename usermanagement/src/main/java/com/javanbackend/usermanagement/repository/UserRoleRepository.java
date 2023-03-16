package com.javanbackend.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javanbackend.usermanagement.model.EnumUserRole;
import com.javanbackend.usermanagement.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByName(EnumUserRole name);
}
