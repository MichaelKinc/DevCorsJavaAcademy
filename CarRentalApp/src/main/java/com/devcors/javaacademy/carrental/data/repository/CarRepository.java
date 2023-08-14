package com.devcors.javaacademy.carrental.data.repository;

import com.devcors.javaacademy.carrental.data.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByBrand(String brand);

    Optional<Car> findCarByLicencePlate(String licencePlate);

}
