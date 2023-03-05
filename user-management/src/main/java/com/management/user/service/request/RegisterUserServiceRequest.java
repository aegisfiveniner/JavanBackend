package com.management.user.service.request;

import com.management.user.enums.TaxRole;
import com.management.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class RegisterUserServiceRequest {

    private String token;

    private String username;

    private String password;

    private UserRole userRole;

    private TaxRole taxRole;

}
