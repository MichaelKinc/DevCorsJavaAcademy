package com.devcors.javaacademy.carrental.service;

import com.devcors.javaacademy.carrental.data.entity.Car;
import com.devcors.javaacademy.carrental.rest.enums.BorrowedCarStatus;

import java.util.List;
import java.util.Optional;

public interface BorrowedCarService {

    BorrowedCarStatus borrowCar(Long userId, Integer carId);

    BorrowedCarStatus returnCar(Long userId, Integer carId);

    Optional<List<Car>> getBorrowedCars(Long userId);
}
