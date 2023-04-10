package com.alurkerja.crud.user;

import com.alurkerja.core.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
}
