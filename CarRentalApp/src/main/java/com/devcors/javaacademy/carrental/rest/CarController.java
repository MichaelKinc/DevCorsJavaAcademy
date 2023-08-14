package com.devcors.javaacademy.carrental.rest;

import com.devcors.javaacademy.carrental.data.dto.CarDTO;
import com.devcors.javaacademy.carrental.data.dto.CarUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.Car;
import com.devcors.javaacademy.carrental.exception.CarNotFoundException;
import com.devcors.javaacademy.carrental.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final RestTemplate restTemplate;

    @GetMapping("/cars")
    public List<Car> getCars() {

        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id) {
        Optional<Car> car = carService.getCarById(id);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        }
        throw new CarNotFoundException("Car with ID " + id + " not found.");
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@Valid @RequestBody CarDTO carDTO) {
        Car car = carService.createNewCar(carDTO);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<? extends Object> deleteCar(@PathVariable Integer id) {
        boolean res = carService.deleteCar(id);
        if (res) {
            restTemplate.delete("/gps/{carId}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new CarNotFoundException("Car with ID " + id + " not found.");
    }

    @PatchMapping("/cars/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @Valid @RequestBody CarUpdateDTO carDTO) {
        Optional<Car> car = carService.updateCar(id, carDTO);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        }
        throw new CarNotFoundException("Car with ID " + id + " not found.");
    }

    @GetMapping("/cars/filter")
    public List<Car> getCar(@RequestParam String brandName) {
        return carService.getCarsByBrand(brandName);
    }

    @GetMapping("/cars/{id}/gps")
    public ResponseEntity<List> getCarLocation(@PathVariable Integer id) {
        Optional<Car> car = carService.getCarById(id);
        if (car.isPresent()) {
            return restTemplate.getForEntity("/gps/{carId}", List.class, id);
        }
        throw new CarNotFoundException("Car with ID " + id + " not found.");
    }


}
