package id.javan.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.javan.user.entity.Role;
import id.javan.user.entity.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleEnum name);
}