package com.devcors.javaacademy.carrental.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BorrowedCar {

    @Id
    @GeneratedValue
    public Integer id;

    public Integer carId;

    public Long userId;

}
