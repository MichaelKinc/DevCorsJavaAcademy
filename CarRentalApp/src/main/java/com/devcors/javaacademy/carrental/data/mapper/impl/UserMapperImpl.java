package com.devcors.javaacademy.carrental.data.mapper.impl;

import com.devcors.javaacademy.carrental.data.dto.UserDTO;
import com.devcors.javaacademy.carrental.data.dto.UserUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.User;
import com.devcors.javaacademy.carrental.data.entity.enums.UserRole;
import com.devcors.javaacademy.carrental.data.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User toUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User.UserBuilder user = User.builder();
        user.role(Enum.valueOf(UserRole.class, userDTO.getRole()));
        user.password(passwordEncoder.encode(userDTO.getPassword()));
        user.lastname(userDTO.getLastname());
        user.firstname(userDTO.getFirstname());
        user.email(userDTO.getEmail());
        user.address(userDTO.getAddress());
        return user.build();
    }

    @Override
    public void update(User user, UserUpdateDTO userDTO) {
        if (userDTO == null) {
            return;
        }

        if (userDTO.getLastname() != null) {
            user.setLastname(userDTO.getLastname());
        }

        if (userDTO.getFirstname() != null) {
            user.setFirstname(userDTO.getFirstname());
        }

        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getAddress() != null) {
            user.setAddress(userDTO.getAddress());
        }

        if (userDTO.getRole() != null) {
            user.setRole(Enum.valueOf(UserRole.class, userDTO.getRole()));
        }

        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
    }
}
