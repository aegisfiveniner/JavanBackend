package com.management.user.validation.validator;

import com.management.user.authentication.impl.JwtAuthenticationImpl;
import com.management.user.enums.UserRole;
import com.management.user.validation.UserRoleMustAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;


public class UserRoleMustAdminValidator implements ConstraintValidator<UserRoleMustAdmin, String> {

    @Autowired
    private JwtAuthenticationImpl jwtAuthentication;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) {
            return true;
        }
        String role = jwtAuthentication.claimToken(value).get("role").toString();
        return role.contains(UserRole.ADMIN.toString());
    }
}
