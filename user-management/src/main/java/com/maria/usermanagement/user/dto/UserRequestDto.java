package com.maria.usermanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "Email must be filled")
    @Email(message = "Must be valid email address")
    private String email;

    @NotBlank(message = "Password must be filled")
    private String password;

    @NotBlank(message = "Role must be filled")
    private String role;
}
