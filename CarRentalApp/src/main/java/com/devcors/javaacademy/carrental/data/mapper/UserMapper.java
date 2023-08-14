package com.devcors.javaacademy.carrental.data.mapper;

import com.devcors.javaacademy.carrental.data.dto.UserDTO;
import com.devcors.javaacademy.carrental.data.dto.UserUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget User user, UserUpdateDTO userDTO);
}
