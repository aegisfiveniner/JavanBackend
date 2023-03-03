// 
// Decompiled by Procyon v0.5.36
// 

package com.assetsManagement.repository;

import com.assetsManagement.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findAllByRole(String role);
}