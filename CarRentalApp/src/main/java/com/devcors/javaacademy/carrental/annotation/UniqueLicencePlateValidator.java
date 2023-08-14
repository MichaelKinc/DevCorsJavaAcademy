package com.devcors.javaacademy.carrental.annotation;

import com.devcors.javaacademy.carrental.annotation.impl.UniqueLicencePlateValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueLicencePlateValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueLicencePlateValidator {
    String message() default "Car with this licence plate already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
