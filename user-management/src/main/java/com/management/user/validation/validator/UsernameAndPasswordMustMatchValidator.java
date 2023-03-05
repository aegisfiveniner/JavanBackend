package com.management.user.validation.validator;

import com.management.user.validation.UsernameAndPasswordMustMatch;
import com.management.user.validation.data.SignInVerificationData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameAndPasswordMustMatchValidator implements ConstraintValidator<UsernameAndPasswordMustMatch, SignInVerificationData> {
    @Override
    public boolean isValid(SignInVerificationData signInVerificationData, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
