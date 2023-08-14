package com.devcors.javaacademy.carrental.service.impl;

import com.devcors.javaacademy.carrental.data.dto.UserDTO;
import com.devcors.javaacademy.carrental.data.dto.UserUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.BorrowedCar;
import com.devcors.javaacademy.carrental.data.entity.User;
import com.devcors.javaacademy.carrental.data.mapper.UserMapper;
import com.devcors.javaacademy.carrental.data.repository.BorrowedCarRepository;
import com.devcors.javaacademy.carrental.data.repository.UserRepository;
import com.devcors.javaacademy.carrental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BorrowedCarRepository borrowedCarRepository;

    private final UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createNewUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            List<BorrowedCar> borrowedCars = borrowedCarRepository.findBorrowedCarsByUserId(id);
            if (!borrowedCars.isEmpty()) {
                borrowedCarRepository.deleteAll(borrowedCars);
            }
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> updateUser(Long id, UserUpdateDTO userDTO) {
        Optional<User> user = getUserById(id);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        User unwrappedUser = user.get();
        userMapper.update(unwrappedUser, userDTO);
        return Optional.of(userRepository.save(unwrappedUser));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
