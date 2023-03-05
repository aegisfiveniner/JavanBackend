package com.management.user.web.model.request;

import com.management.user.enums.TaxRole;
import com.management.user.enums.UserRole;
import com.management.user.validation.UsernameMustUnique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserWebRequest {

    @UsernameMustUnique(message = "Username already taken!")
    private String username;

    private String password;

    private UserRole userRole;

    private TaxRole taxRole;

    private String fullName;

    private String phoneNumber;

}
