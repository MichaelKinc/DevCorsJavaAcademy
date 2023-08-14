package com.devcors.javaacademy.carrental.data.mapper.impl;

import com.devcors.javaacademy.carrental.data.dto.CarDTO;
import com.devcors.javaacademy.carrental.data.dto.CarUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.Car;
import com.devcors.javaacademy.carrental.data.entity.enums.CarColor;
import com.devcors.javaacademy.carrental.data.entity.enums.CarType;
import com.devcors.javaacademy.carrental.data.mapper.CarMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapperImpl implements CarMapper {
    @Override
    public Car toCar(CarDTO carDTO) {
        if (carDTO == null) {
            return null;
        }
        Car.CarBuilder car = Car.builder();

        car.year(carDTO.getYear());
        car.brand(carDTO.getBrand());
        car.licencePlate(carDTO.getLicencePlate());
        if (carDTO.getType() != null) {
            car.type(Enum.valueOf(CarType.class, carDTO.getType()));
        }
        if (carDTO.getColor() != null) {
            car.color(Enum.valueOf(CarColor.class, carDTO.getColor()));
        }

        return car.build();
    }

    @Override
    public void update(Car car, CarUpdateDTO carDTO) {
        if (carDTO == null) {
            return;
        }
        if (carDTO.getYear() != null) {
            car.setYear(carDTO.getYear());
        }
        if (carDTO.getBrand() != null) {
            car.setBrand(carDTO.getBrand());
        }
        if (carDTO.getLicencePlate() != null) {
            car.setLicencePlate(carDTO.getLicencePlate());
        }
        if (carDTO.getType() != null) {
            car.setType(Enum.valueOf(CarType.class, carDTO.getType()));
        }
        if (carDTO.getColor() != null) {
            car.setColor(Enum.valueOf(CarColor.class, carDTO.getColor()));
        }
    }
}
