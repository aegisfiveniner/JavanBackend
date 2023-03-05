package com.management.user.validation.validator;

import com.management.user.repository.UserRepository;
import com.management.user.validation.UserIdMustExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIdMustExistValidator implements ConstraintValidator<UserIdMustExist, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.existsById(value);
    }
}
