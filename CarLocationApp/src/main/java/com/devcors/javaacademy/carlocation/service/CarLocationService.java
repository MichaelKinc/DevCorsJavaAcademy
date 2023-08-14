package com.devcors.javaacademy.carlocation.service;

import com.devcors.javaacademy.carlocation.data.dto.CarLocationDTO;
import com.devcors.javaacademy.carlocation.data.entity.CarLocation;

import java.util.List;

public interface CarLocationService {
    List<CarLocation> getAllCarLocations(Integer carId);

    CarLocation saveNewCarLocation(CarLocationDTO carLocationDTO);

    void deleteAllCarLocations(Integer carId);
}
