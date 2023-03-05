package com.management.user.validation.validator;

import com.management.user.repository.UserRepository;
import com.management.user.validation.UsernameMustUnique;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameMustUniqueValidator implements ConstraintValidator<UsernameMustUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByUsername(value);
    }
}
