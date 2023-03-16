package com.javanbackend.usermanagement.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBody {
    @NotBlank(message = "Email must not be blank")
    @Size(min = 6, max = 50, message = "Email must have 6 to 50 characters")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, max = 30, message = "Password must have 8 to 30 characters")
    private String password;

    @NotNull
    private Integer roleId;

    @NotNull
    private Integer userRoleId;
}
