package id.javan.tax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.javan.tax.entity.Role;
import id.javan.tax.entity.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleEnum name);
}