package com.devcors.javaacademy.carlocation.data.mapper.impl;

import com.devcors.javaacademy.carlocation.data.dto.CarLocationDTO;
import com.devcors.javaacademy.carlocation.data.entity.CarLocation;
import com.devcors.javaacademy.carlocation.data.mapper.CarLocationMapper;
import org.springframework.stereotype.Component;

@Component
public class CarLocationMapperImpl implements CarLocationMapper {
    @Override
    public CarLocation toCarLocation(CarLocationDTO carLocationDTO) {
        return CarLocation.builder().latitude(carLocationDTO.getLatitude()).longitude(carLocationDTO.getLongitude()).carId(carLocationDTO.getCarId()).build();
    }
}
