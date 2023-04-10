package com.alurkerja.crud.user;

import com.alurkerja.core.entity.CrudEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.Max;

@Entity
@Getter
@Setter
public class User extends CrudEntity {
    private String email;
    private String password;
    private String role;

}
