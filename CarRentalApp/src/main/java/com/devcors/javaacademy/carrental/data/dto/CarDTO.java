package com.devcors.javaacademy.carrental.data.dto;

import com.devcors.javaacademy.carrental.annotation.EnumValidator;
import com.devcors.javaacademy.carrental.data.entity.enums.CarColor;
import com.devcors.javaacademy.carrental.data.entity.enums.CarType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarDTO {

    @NotNull(message = "Year is required.")
    @Min(value = 1000, message = "Year has to be in format YYYY.")
    @Max(value = 9999, message = "Year has to be in format YYYY.")
    private Short year;

    @NotBlank(message = "Brand is required.")
    private String brand;

    @NotBlank(message = "Licence plate is required.")
    private String licencePlate;

    @NotBlank(message = "Car type is required.")
    @EnumValidator(enumClass = CarType.class)
    private String type;

    @NotBlank(message = "Color is required")
    @EnumValidator(enumClass = CarColor.class)
    private String color;
}
