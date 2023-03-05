package com.management.user.validation.validator;

import com.management.user.authentication.impl.JwtAuthenticationImpl;
import com.management.user.enums.TaxRole;
import com.management.user.validation.UserTaxRoleMustMatch;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UserTaxRoleMustMatchValidator implements ConstraintValidator<UserTaxRoleMustMatch, String> {

    @Autowired
    private JwtAuthenticationImpl jwtAuthentication;

    private TaxRole taxRole;

    @Override
    public void initialize(UserTaxRoleMustMatch requiredIfChecked) {
        this.taxRole = requiredIfChecked.taxRole();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) {
            return true;
        }
        String role = jwtAuthentication.claimToken(value).get("tax-role").toString();
        return role.contains(taxRole.toString());
    }
}
