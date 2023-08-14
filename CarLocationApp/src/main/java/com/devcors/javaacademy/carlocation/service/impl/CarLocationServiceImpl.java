package com.devcors.javaacademy.carlocation.service.impl;

import com.devcors.javaacademy.carlocation.data.dto.CarLocationDTO;
import com.devcors.javaacademy.carlocation.data.entity.CarLocation;
import com.devcors.javaacademy.carlocation.data.mapper.CarLocationMapper;
import com.devcors.javaacademy.carlocation.data.repository.CarLocationRepository;
import com.devcors.javaacademy.carlocation.service.CarLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarLocationServiceImpl implements CarLocationService {

    private final CarLocationRepository carLocationRepository;
    private final CarLocationMapper carLocationMapper;

    @Override
    public List<CarLocation> getAllCarLocations(Integer carId) {
        return carLocationRepository.findCarLocationByCarId(carId);
    }

    @Override
    public CarLocation saveNewCarLocation(CarLocationDTO carLocationDTO) {
        CarLocation carLocation = carLocationMapper.toCarLocation(carLocationDTO);
        carLocationRepository.save(carLocation);
        return carLocation;
    }

    @Override
    public void deleteAllCarLocations(Integer carId) {
        carLocationRepository.deleteAll(carLocationRepository.findCarLocationByCarId(carId));
    }
}
