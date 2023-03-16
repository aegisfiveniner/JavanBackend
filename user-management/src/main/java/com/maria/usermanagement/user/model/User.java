package com.maria.usermanagement.user.model;

import com.maria.usermanagement.base.BaseModel;
import com.maria.usermanagement.enumerator.EnumRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class User extends BaseModel {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
