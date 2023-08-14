package com.devcors.javaacademy.carrental.data.dto;

import com.devcors.javaacademy.carrental.annotation.EnumValidator;
import com.devcors.javaacademy.carrental.annotation.NullOrNotBlankValidator;
import com.devcors.javaacademy.carrental.annotation.NullOrValidYearValidator;
import com.devcors.javaacademy.carrental.data.entity.enums.CarColor;
import com.devcors.javaacademy.carrental.data.entity.enums.CarType;
import lombok.Data;

@Data
public class CarUpdateDTO {

    @NullOrValidYearValidator
    private Short year;

    @NullOrNotBlankValidator(message = "Brand cannot be blank.")
    private String brand;

    @NullOrNotBlankValidator(message = "Licence plate cannot be blank.")
    private String licencePlate;

    @NullOrNotBlankValidator(message = "Car type cannot be blank.")
    @EnumValidator(enumClass = CarType.class)
    private String type;

    @NullOrNotBlankValidator(message = "Car color cannot be blank.")
    @EnumValidator(enumClass = CarColor.class)
    private String color;
}
