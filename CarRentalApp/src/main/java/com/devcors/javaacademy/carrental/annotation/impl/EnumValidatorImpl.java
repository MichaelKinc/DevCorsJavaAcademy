package com.devcors.javaacademy.carrental.annotation.impl;

import com.devcors.javaacademy.carrental.annotation.EnumValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private List<String> names;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        this.names = Stream.of(constraintAnnotation.enumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (s == null) {
            return true;
        }
        if (!names.contains(s)) {
            String allowedValues = names.stream().map(String::valueOf).collect(Collectors.joining(", "));
            createMessage(constraintValidatorContext, "The value is wrong. Only one of these is allowed: " + allowedValues + ".");
            return false;
        }
        return true;
    }

    private void createMessage(ConstraintValidatorContext constraintValidatorContext, String message) {
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
