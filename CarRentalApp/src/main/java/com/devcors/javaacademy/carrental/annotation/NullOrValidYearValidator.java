package com.devcors.javaacademy.carrental.annotation;

import com.devcors.javaacademy.carrental.annotation.impl.NullOrValidYearValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrValidYearValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NullOrValidYearValidator {
    String message() default "Year has to be in format YYYY.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
