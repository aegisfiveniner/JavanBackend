package com.javan.server.repository;

import com.javan.server.table.TableUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<TableUser, String> {
}
