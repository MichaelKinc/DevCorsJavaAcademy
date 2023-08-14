package com.devcors.javaacademy.carrental.annotation;

import com.devcors.javaacademy.carrental.annotation.impl.EnumValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EnumValidator {
    Class<? extends Enum<?>> enumClass();

    String message() default "must be any of enum {enumClass.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
