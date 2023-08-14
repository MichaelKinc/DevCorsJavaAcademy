package com.devcors.javaacademy.lesson6;

import com.devcors.javaacademy.carrental.CarRentalApplication;
import com.devcors.javaacademy.carrental.data.entity.BorrowedCar;
import com.devcors.javaacademy.carrental.data.entity.Car;
import com.devcors.javaacademy.carrental.data.entity.User;
import com.devcors.javaacademy.carrental.data.entity.enums.CarColor;
import com.devcors.javaacademy.carrental.data.entity.enums.CarType;
import com.devcors.javaacademy.carrental.data.entity.enums.UserRole;
import com.devcors.javaacademy.carrental.data.repository.BorrowedCarRepository;
import com.devcors.javaacademy.carrental.data.repository.CarRepository;
import com.devcors.javaacademy.carrental.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = CarRentalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class BorrowedCarControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowedCarRepository borrowedCarRepository;

    public static final String EMAIL_1 = "admin@email.com";
    public static final String FIRSTNAME_1 = "Firstname";
    public static final String LASTNAME_1 = "Lastname";
    public static final String ADDRESS_1 = "Downing street 10";

    public static final String EMAIL_2 = "user@email.com";

    private static final User USER_1 = User.builder()
            .email(EMAIL_1)
            .firstname(FIRSTNAME_1)
            .lastname(LASTNAME_1)
            .role(UserRole.ADMIN)
            .address(ADDRESS_1)
            .build();

    private static final User USER_2 = User.builder()
            .email(EMAIL_2)
            .firstname("Firstname")
            .lastname("Lastname")
            .role(UserRole.USER)
            .address("Baker street 10")
            .build();

    @Autowired
    private CarRepository carRepository;

    private static final String LICENCE_PLATE_1 = "4H44444";
    private static final String LICENCE_PLATE_2 = "5H55555";

    private static final String BRAND_1 = "BMW";
    private static final String BRAND_2 = "Audi";

    private static final short YEAR_1 = (short) 1999;
    private static final short YEAR_2 = (short) 2002;

    private static final Car CAR_1 = Car.builder()
            .brand(BRAND_1)
            .year(YEAR_1)
            .licencePlate(LICENCE_PLATE_1)
            .color(CarColor.BLACK)
            .type(CarType.HATCHBACK)
            .build();

    private static final Car CAR_2 = Car.builder()
            .brand(BRAND_2)
            .year(YEAR_2)
            .licencePlate(LICENCE_PLATE_2)
            .color(CarColor.PINK)
            .type(CarType.COMBI)
            .build();

    @Test
    @WithMockUser(username = "user1@email.cz", authorities = { "ADMIN" }, password = "password")
    void createBorrowingShouldReturnOk() {
        User savedUser = userRepository.save(USER_1);
        Long userId = savedUser.getId();
        Car savedCar = carRepository.save(CAR_1);
        Integer carId = savedCar.getId();
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user1@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        webTestClient.put()
                .uri(ub -> ub.path("/users/"+userId+"/car/borrow/"+carId).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @WithMockUser(username = "user1@email.cz", authorities = { "ADMIN" }, password = "password")
    void createBorrowingShouldReturnNotFound() {
        User savedUser2 = userRepository.save(USER_2);
        Long userId2 = savedUser2.getId();
        Car savedCar2 = carRepository.save(CAR_2);
        Integer carId2 = savedCar2.getId();

        borrowedCarRepository.save(BorrowedCar.builder()
                .carId(carId2)
                .userId(userId2).build());
        System.out.println(borrowedCarRepository.findAll());
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user1@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        webTestClient.put()
                .uri(ub -> ub.path("/users/"+userId2+"/car/borrow/"+carId2+1).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .exchange()
                .expectStatus()
                .isNotFound();

    }

    @Test
    @WithMockUser(username = "user1@email.cz", authorities = { "ADMIN" }, password = "password")
    void returnCarShouldReturnOk() {
        User savedUser = userRepository.save(USER_1);
        Long userId = savedUser.getId();
        Car savedCar = carRepository.save(CAR_1);
        Integer carId = savedCar.getId();
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user1@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        List<BorrowedCar> borrowedCarOptional = borrowedCarRepository.findAll();

        System.out.println(borrowedCarOptional);
        borrowedCarRepository.save(BorrowedCar.builder()
                .carId(carId)
                .userId(userId).build());
        webTestClient.put()
                .uri(ub -> ub.path("/users/"+userId+"/car/return/"+carId).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @WithMockUser(username = "user1@email.cz", authorities = { "ADMIN" }, password = "password")
    void returnCarShouldReturnNotFound() {
        User savedUser = userRepository.save(USER_1);
        Long userId = savedUser.getId();
        Car savedCar = carRepository.save(CAR_1);
        Integer carId = savedCar.getId();

        User savedUser2 = userRepository.save(USER_2);
        Long userId2 = savedUser2.getId();
        Car savedCar2 = carRepository.save(CAR_2);
        Integer carId2 = savedCar2.getId();
        String encodedAuthString = java.util.Base64.getEncoder().encodeToString(("user1@email.cz" + ":" + "password").getBytes(StandardCharsets.UTF_8));
        borrowedCarRepository.save(BorrowedCar.builder()
                .carId(carId)
                .userId(userId).build());

        webTestClient.put()
                .uri(ub -> ub.path("/users/"+userId2+"/car/return/"+carId).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthString)
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
