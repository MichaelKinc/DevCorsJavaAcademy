package com.devcors.javaacademy.carrental.annotation.impl;

import com.devcors.javaacademy.carrental.annotation.NullOrNotBlankValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidatorImpl implements ConstraintValidator<NullOrNotBlankValidator, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        return s.trim().length() > 0;
    }
}
