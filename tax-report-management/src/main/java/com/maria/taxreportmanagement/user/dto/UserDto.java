package com.maria.taxreportmanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String id;
    private String email;
    private String password;
    private String role;
    private String createdBy;
    private String updatedBy;
    private boolean isDeleted;
}
