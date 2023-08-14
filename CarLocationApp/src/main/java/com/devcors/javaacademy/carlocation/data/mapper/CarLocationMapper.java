package com.devcors.javaacademy.carlocation.data.mapper;

import com.devcors.javaacademy.carlocation.data.dto.CarLocationDTO;
import com.devcors.javaacademy.carlocation.data.entity.CarLocation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarLocationMapper {
    CarLocation toCarLocation(CarLocationDTO carLocationDTO);
}
