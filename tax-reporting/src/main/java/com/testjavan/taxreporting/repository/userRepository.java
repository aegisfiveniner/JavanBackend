package com.testjavan.taxreporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testjavan.taxreporting.models.User;


public interface userRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users " +
            "WHERE id = 1", nativeQuery = true)
    User finduser();
}
