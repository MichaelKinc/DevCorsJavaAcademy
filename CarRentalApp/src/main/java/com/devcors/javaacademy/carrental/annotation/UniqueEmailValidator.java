package com.devcors.javaacademy.carrental.annotation;

import com.devcors.javaacademy.carrental.annotation.impl.UniqueEmailValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueEmailValidator {
    String message() default "User with this email already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
