package com.devcors.javaacademy.carrental.service;

import com.devcors.javaacademy.carrental.data.dto.CarDTO;
import com.devcors.javaacademy.carrental.data.dto.CarUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();

    Optional<Car> getCarById(Integer id);

    Car createNewCar(CarDTO carDTO);

    boolean deleteCar(Integer id);

    Optional<Car> updateCar(Integer id, CarUpdateDTO carDTO);

    List<Car> getCarsByBrand(String brand);

    Optional<Car> getCarByLicencePlate(String licencePlace);

}
