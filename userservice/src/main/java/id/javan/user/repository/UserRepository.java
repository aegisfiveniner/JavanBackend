package id.javan.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.javan.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  // method available: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
  // List<User> findByRole(String role);
}