package com.devcors.javaacademy.carrental.service;

import com.devcors.javaacademy.carrental.data.dto.UserDTO;
import com.devcors.javaacademy.carrental.data.dto.UserUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createNewUser(UserDTO userDTO);

    boolean deleteUser(Long id);

    Optional<User> updateUser(Long id, UserUpdateDTO userDTO);

    Optional<User> getUserByEmail(String email);
}
