package com.devcors.javaacademy.carlocation.data.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarLocationDTO {

    @NotBlank(message = "Car ID is required.")
    private Integer carId;

    @NotBlank(message = "Latitude is required.")
    private Double latitude;

    @NotBlank(message = "Longitude is required.")
    private Double longitude;
}