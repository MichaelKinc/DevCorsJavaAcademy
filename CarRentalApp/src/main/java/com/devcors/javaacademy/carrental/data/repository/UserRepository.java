package com.devcors.javaacademy.carrental.data.repository;

import com.devcors.javaacademy.carrental.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

}
