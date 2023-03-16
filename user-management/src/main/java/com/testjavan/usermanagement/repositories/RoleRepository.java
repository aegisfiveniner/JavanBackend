package com.testjavan.usermanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.testjavan.usermanagement.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

@Query(value = "SELECT * FROM role WHERE role_name  = ?1", nativeQuery = true)
public Role findByName(String roleName);
}
