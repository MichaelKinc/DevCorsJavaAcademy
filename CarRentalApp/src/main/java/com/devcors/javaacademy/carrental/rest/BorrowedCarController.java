package com.devcors.javaacademy.carrental.rest;

import com.devcors.javaacademy.carrental.data.entity.Car;
import com.devcors.javaacademy.carrental.exception.BorrowingNotFoundException;
import com.devcors.javaacademy.carrental.exception.CarAlreadyBorrowedException;
import com.devcors.javaacademy.carrental.exception.CarNotFoundException;
import com.devcors.javaacademy.carrental.exception.UserNotFoundException;
import com.devcors.javaacademy.carrental.rest.enums.BorrowedCarStatus;
import com.devcors.javaacademy.carrental.service.BorrowedCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BorrowedCarController {

    public final BorrowedCarService borrowedCarService;

    @PutMapping("/users/{userId}/car/borrow/{carId}")
    public ResponseEntity<String> borrowCar(@PathVariable Long userId, @PathVariable Integer carId) {
        BorrowedCarStatus borrowedCarStatus = borrowedCarService.borrowCar(userId, carId);
        if (borrowedCarStatus == BorrowedCarStatus.USER_NOT_FOUND) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");

        }
        if (borrowedCarStatus == BorrowedCarStatus.CAR_NOT_FOUND) {
            throw new CarNotFoundException("Car with ID " + carId + " not found.");

        }
        if (borrowedCarStatus == BorrowedCarStatus.ALREADY_BORROWED) {
            throw new CarAlreadyBorrowedException("Car with ID " + carId + " is already borrowed.");

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/car/return/{carId}")
    public ResponseEntity<String> returnCar(@PathVariable Long userId, @PathVariable Integer carId) {
        BorrowedCarStatus borrowedCarStatus = borrowedCarService.returnCar(userId, carId);
        if (borrowedCarStatus == BorrowedCarStatus.USER_NOT_FOUND) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        if (borrowedCarStatus == BorrowedCarStatus.CAR_NOT_FOUND) {
            throw new CarNotFoundException("Car with ID " + carId + " not found.");
        }
        if (borrowedCarStatus == BorrowedCarStatus.BORROWING_NOT_FOUND) {
            throw new BorrowingNotFoundException("Borrowing not found.");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/car")
    public ResponseEntity<List<Car>> getBorrowedCars(@PathVariable Long userId) {
        Optional<List<Car>> cars = borrowedCarService.getBorrowedCars(userId);
        if (cars.isEmpty()) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        return new ResponseEntity<>(cars.get(), HttpStatus.OK);
    }
}
