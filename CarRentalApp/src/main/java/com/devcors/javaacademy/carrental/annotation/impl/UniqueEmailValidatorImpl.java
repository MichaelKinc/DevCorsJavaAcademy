package com.devcors.javaacademy.carrental.annotation.impl;

import com.devcors.javaacademy.carrental.annotation.UniqueEmailValidator;
import com.devcors.javaacademy.carrental.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidatorImpl implements ConstraintValidator<UniqueEmailValidator, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.getUserByEmail(s).isPresent();
    }
}
