package com.devcors.javaacademy.carrental.service.impl;

import com.devcors.javaacademy.carrental.data.entity.BorrowedCar;
import com.devcors.javaacademy.carrental.data.entity.Car;
import com.devcors.javaacademy.carrental.data.entity.User;
import com.devcors.javaacademy.carrental.data.repository.BorrowedCarRepository;
import com.devcors.javaacademy.carrental.data.repository.CarRepository;
import com.devcors.javaacademy.carrental.rest.enums.BorrowedCarStatus;
import com.devcors.javaacademy.carrental.service.BorrowedCarService;
import com.devcors.javaacademy.carrental.service.CarService;
import com.devcors.javaacademy.carrental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowedCarServiceImpl implements BorrowedCarService {

    private final BorrowedCarRepository borrowedCarRepository;

    private final UserService userService;

    private final CarService carService;

    private final CarRepository carRepository;

    @Override
    public BorrowedCarStatus borrowCar(Long userId, Integer carId) {
        Optional<User> user = userService.getUserById(userId);
        Optional<Car> car = carService.getCarById(carId);
        if (!car.isPresent()) {
            return BorrowedCarStatus.CAR_NOT_FOUND;
        }
        if (!user.isPresent()) {
            return BorrowedCarStatus.USER_NOT_FOUND;
        }
        Optional<BorrowedCar> borrowedCarOptional = borrowedCarRepository.findBorrowedCarByCarId(carId);
        if (borrowedCarOptional.isPresent()) {
            return BorrowedCarStatus.ALREADY_BORROWED;
        }
        BorrowedCar borrowedCar = BorrowedCar.builder()
                .carId(carId)
                .userId(userId).build();
        borrowedCarRepository.save(borrowedCar);
        return BorrowedCarStatus.SUCCESS;
    }

    @Override
    public BorrowedCarStatus returnCar(Long userId, Integer carId) {
        Optional<User> user = userService.getUserById(userId);
        Optional<Car> car = carService.getCarById(carId);
        if (!car.isPresent()) {
            return BorrowedCarStatus.CAR_NOT_FOUND;
        }
        if (!user.isPresent()) {
            return BorrowedCarStatus.USER_NOT_FOUND;
        }
        Optional<BorrowedCar> borrowedCarOptional = borrowedCarRepository.findBorrowedCarByCarIdAndUserId(carId, userId);
        if (!borrowedCarOptional.isPresent()) {
            return BorrowedCarStatus.BORROWING_NOT_FOUND;
        }
        borrowedCarRepository.delete(borrowedCarOptional.get());
        return BorrowedCarStatus.SUCCESS;
    }

    @Override
    public Optional<List<Car>> getBorrowedCars(Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            return Optional.empty();
        }
        List<BorrowedCar> borrowedCars = borrowedCarRepository.findBorrowedCarByUserId(userId);
        if (borrowedCars.isEmpty()) {
            return Optional.of(new ArrayList<>(0));
        }
        List<Car> cars = new ArrayList<>();
        for (BorrowedCar borrowedCar : borrowedCars) {
            cars.add(carService.getCarById(borrowedCar.getCarId()).get());
        }
        return Optional.of(cars);
    }
}
