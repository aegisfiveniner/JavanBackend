package com.maria.usermanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String id;
    private String email;
    private String password;
    private String role;
    private boolean isDeleted;
    private String createdBy;
    private String updatedBy;
}
