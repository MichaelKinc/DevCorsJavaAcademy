package com.devcors.javaacademy.carrental.data.repository;

import com.devcors.javaacademy.carrental.data.entity.BorrowedCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowedCarRepository extends JpaRepository<BorrowedCar, Integer> {

    Optional<BorrowedCar> findBorrowedCarByCarIdAndUserId(Integer carId, Long userId);

    List<BorrowedCar> findBorrowedCarByUserId(Long userId);

    Optional<BorrowedCar> findBorrowedCarByCarId(Integer carId);

    List<BorrowedCar> findBorrowedCarsByCarId(Integer carId);

    List<BorrowedCar> findBorrowedCarsByUserId(Long userId);
}
