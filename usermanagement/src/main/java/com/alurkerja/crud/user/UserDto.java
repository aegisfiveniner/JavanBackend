package com.alurkerja.crud.user;

import com.alurkerja.spec.entity.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.util.UUID;

@Getter
@Setter
public class UserDto extends BaseDto<User, UserDto> {
    private String email;
    private String password;
    private String role;
    private String createdBy;
    private String updatedBy;
}
