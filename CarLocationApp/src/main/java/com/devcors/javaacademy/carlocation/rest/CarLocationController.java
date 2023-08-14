package com.devcors.javaacademy.carlocation.rest;

import com.devcors.javaacademy.carlocation.data.entity.CarLocation;
import com.devcors.javaacademy.carlocation.service.CarLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarLocationController {

    private final CarLocationService carLocationService;

    @GetMapping("/gps/{carId}")
    public List<CarLocation> getCarLocations(@PathVariable Integer carId) {
        return carLocationService.getAllCarLocations(carId);
    }

    @DeleteMapping("/gps/{carId}")
    public void deleteCarLocations(@PathVariable Integer carId) {
        carLocationService.deleteAllCarLocations(carId);
    }
}
