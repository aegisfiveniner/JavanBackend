package com.management.user.web.model.response;

import com.management.user.enums.TaxRole;
import com.management.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInWebResponse {

    private Long id;

    private String token;

    private String username;

    private UserRole userRole;

    private TaxRole taxRole;

    private String fullName;

    private String phoneNumber;
}
