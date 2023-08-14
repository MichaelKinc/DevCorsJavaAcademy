package com.devcors.javaacademy.carrental.service.impl;

import com.devcors.javaacademy.carrental.data.entity.User;
import com.devcors.javaacademy.carrental.data.entity.enums.UserRole;
import com.devcors.javaacademy.carrental.data.repository.UserRepository;
import com.devcors.javaacademy.carrental.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
    }

    @PostConstruct
    void init() {
        User user = User.builder()
                .email("user1@email.cz")
                .password(passwordEncoder.encode("password"))
                .role(UserRole.ADMIN)
                .build();
        User user2 = User.builder()
                .email("user2@email.cz")
                .password(passwordEncoder.encode("password"))
                .role(UserRole.USER)
                .build();
        userRepository.save(user);
        userRepository.save(user2);
    }
}
