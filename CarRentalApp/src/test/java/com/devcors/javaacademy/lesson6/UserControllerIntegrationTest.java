package com.devcors.javaacademy.lesson6;

import com.devcors.javaacademy.carrental.CarRentalApplication;
import com.devcors.javaacademy.carrental.data.dto.UserDTO;
import com.devcors.javaacademy.carrental.data.entity.enums.UserRole;
import com.devcors.javaacademy.carrental.data.entity.User;
import com.devcors.javaacademy.carrental.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CarRentalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class UserControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    public static final String EMAIL_1 = "test@email.com";
    public static final String FIRSTNAME_1 = "Firstname";
    public static final String LASTNAME_1 = "Lastname";
    public static final String ADDRESS_1 = "Downing street 10";

    public static final String EMAIL_2 = "user@email.com";

    public static final String EMAIL_3 = "test@test.cz";

    private static final User USER_1 = User.builder()
            .email(EMAIL_1)
            .firstname(FIRSTNAME_1)
            .lastname(LASTNAME_1)
            .role(UserRole.ADMIN)
            .address(ADDRESS_1)
            .password("password")
            .build();

    private static final UserDTO USER_1_DTO = UserDTO.builder()
            .email(EMAIL_3)
            .firstname(FIRSTNAME_1)
            .lastname(LASTNAME_1)
            .role(UserRole.ADMIN.name())
            .address(ADDRESS_1)
            .password("password")
            .build();

    private static final User USER_2 = User.builder()
            .email(EMAIL_2)
            .firstname("Firstname")
            .lastname("Lastname")
            .role(UserRole.USER)
            .address("Baker street 10")
            .password("password")
            .build();

    @Test
    @WithMockUser(username = "user1@email.cz", authorities = { "ADMIN" }, password = "password")
    void createUserShouldReturnOk() {
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user1@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        webTestClient.post()
                .uri(ub -> ub.path("/users").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .bodyValue(USER_1_DTO)
                .exchange()
                .expectStatus()
                .isOk();

        List<User> users = userRepository.findAll();
        assertFalse(CollectionUtils.isEmpty(users));
    }

    @Test
    @WithMockUser(username = "user2@email.cz", authorities = { "USER" }, password = "password")
    void updateUserShouldReturnOk() {
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user2@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        User savedUser = userRepository.save(USER_1);
        Long userId = savedUser.getId();

        savedUser.setId(null);
        savedUser.setRole(UserRole.USER);
        webTestClient.patch()
                .uri(ub -> ub.path("/users/" + userId).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .bodyValue(savedUser)
                .exchange()
                .expectStatus()
                .isOk();

        Optional<User> user = userRepository.findById(userId);
        assertTrue(user.isPresent());
        assertEquals(UserRole.USER, user.get().getRole());
    }

    @Test
    @WithMockUser(username = "user2@email.cz", authorities = { "USER" }, password = "password")
    void updateUserByIdShouldReturnBadRequest() {
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user2@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        User savedUser = userRepository.save(USER_1);

        webTestClient.put()
                .uri(ub -> ub.path("/users/" + (savedUser.getId() + 1)).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .bodyValue(savedUser)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @WithMockUser(username = "user2@email.cz", authorities = { "USER" }, password = "password")
    void getAllUsersShouldReturnOk() {
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user2@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        userRepository.save(USER_1);
        userRepository.save(USER_2);

        webTestClient.get()
                .uri(ub -> ub.path("/users").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @WithMockUser(username = "user1@email.cz", authorities = { "ADMIN" }, password = "password")
    void getUserByIdShouldReturnOk() {
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user1@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        User savedUser = userRepository.save(USER_1);

        webTestClient.get()
                .uri(ub -> ub.path("/users/1").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @WithMockUser(username = "user1@email.cz", authorities = { "ADMIN" }, password = "password")
    void getUserByIdShouldReturnNotFound() {
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user1@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        User savedUser = userRepository.save(USER_1);

        webTestClient.get()
                .uri(ub -> ub.path("/users/298928").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @WithMockUser(username = "user2@email.cz", authorities = { "USER" }, password = "password")
    void deleteUserShouldReturnOk() {
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user2@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        User savedUser = userRepository.save(USER_1);
        userRepository.save(USER_2);

        webTestClient.delete()
                .uri(ub -> ub.path("/users/" + savedUser.getId()).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .exchange()
                .expectStatus()
                .isOk();

        List<User> users = userRepository.findAll();
        assertFalse(CollectionUtils.isEmpty(users));
        assertEquals(4, users.size());
    }
}
