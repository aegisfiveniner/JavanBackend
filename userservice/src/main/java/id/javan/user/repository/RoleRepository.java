package id.javan.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.javan.user.entity.Role;
import id.javan.user.entity.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(RoleEnum name);
}