package id.javan.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.javan.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}