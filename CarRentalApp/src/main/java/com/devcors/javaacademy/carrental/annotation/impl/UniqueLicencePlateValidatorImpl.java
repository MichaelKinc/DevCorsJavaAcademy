package com.devcors.javaacademy.carrental.annotation.impl;


import com.devcors.javaacademy.carrental.annotation.UniqueLicencePlateValidator;
import com.devcors.javaacademy.carrental.service.CarService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueLicencePlateValidatorImpl implements ConstraintValidator<UniqueLicencePlateValidator, String> {

    @Autowired
    private CarService carService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return carService.getCarByLicencePlate(s).isEmpty();
    }
}
