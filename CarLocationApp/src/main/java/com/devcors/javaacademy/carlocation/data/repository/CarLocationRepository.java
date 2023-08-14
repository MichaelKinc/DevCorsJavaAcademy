package com.devcors.javaacademy.carlocation.data.repository;

import com.devcors.javaacademy.carlocation.data.entity.CarLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarLocationRepository extends JpaRepository<CarLocation, Integer> {
    List<CarLocation> findCarLocationByCarId(Integer carId);
}
