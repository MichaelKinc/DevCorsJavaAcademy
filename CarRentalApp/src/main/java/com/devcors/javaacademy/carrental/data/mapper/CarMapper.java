package com.devcors.javaacademy.carrental.data.mapper;

import com.devcors.javaacademy.carrental.data.dto.CarDTO;
import com.devcors.javaacademy.carrental.data.dto.CarUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.Car;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toCar(CarDTO carDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Car car, CarUpdateDTO carDTO);
}
