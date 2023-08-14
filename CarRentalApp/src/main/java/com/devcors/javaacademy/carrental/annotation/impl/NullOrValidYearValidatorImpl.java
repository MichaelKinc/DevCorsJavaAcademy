package com.devcors.javaacademy.carrental.annotation.impl;


import com.devcors.javaacademy.carrental.annotation.NullOrValidYearValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrValidYearValidatorImpl implements ConstraintValidator<NullOrValidYearValidator, Short> {

    @Override
    public boolean isValid(Short year, ConstraintValidatorContext constraintValidatorContext) {
        if (year == null) {
            return true;
        }
        return year >= 1000 && year <= 9999;
    }
}
