package com.devcors.javaacademy.carrental.rest;

import com.devcors.javaacademy.carrental.data.dto.UserDTO;
import com.devcors.javaacademy.carrental.data.dto.UserUpdateDTO;
import com.devcors.javaacademy.carrental.data.entity.User;
import com.devcors.javaacademy.carrental.exception.UserNotFoundException;
import com.devcors.javaacademy.carrental.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")

    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        throw new UserNotFoundException("User with ID " + id + " not found.");
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.createNewUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean res = userService.deleteUser(id);
        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new UserNotFoundException("User with ID " + id + " not found.");
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userDTO) {
        Optional<User> user = userService.updateUser(id, userDTO);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        throw new UserNotFoundException("User with ID " + id + " not found.");
    }
}
