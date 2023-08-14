package com.devcors.javaacademy.carrental.service.impl;

import com.devcors.javaacademy.carrental.data.dto.CarDTO;
import com.devcors.javaacademy.carrental.data.dto.CarUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.BorrowedCar;
import com.devcors.javaacademy.carrental.data.entity.Car;
import com.devcors.javaacademy.carrental.data.mapper.CarMapper;
import com.devcors.javaacademy.carrental.data.repository.BorrowedCarRepository;
import com.devcors.javaacademy.carrental.data.repository.CarRepository;
import com.devcors.javaacademy.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final BorrowedCarRepository borrowedCarRepository;

    private final CarMapper carMapper;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public Car createNewCar(CarDTO carDTO) {
        Car car = carMapper.toCar(carDTO);
        return carRepository.save(car);
    }

    @Override
    public boolean deleteCar(Integer id) {
        Optional<Car> car = getCarById(id);
        if (car.isPresent()) {
            carRepository.delete(car.get());
            List<BorrowedCar> borrowedCars = borrowedCarRepository.findBorrowedCarsByCarId(id);
            if (!borrowedCars.isEmpty()) {
                borrowedCarRepository.deleteAll(borrowedCars);
            }
            return true;
        }
        return false;
    }

    @Override
    public Optional<Car> updateCar(Integer id, CarUpdateDTO carDTO) {
        Optional<Car> car = getCarById(id);
        if (car.isEmpty()) {
            return Optional.empty();
        }
        Car unwrappedCar = car.get();
        carMapper.update(unwrappedCar, carDTO);
        return Optional.of(carRepository.save(unwrappedCar));
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    @Override
    public Optional<Car> getCarByLicencePlate(String licencePlace) {
        return carRepository.findCarByLicencePlate(licencePlace);
    }
}
