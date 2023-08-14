package com.devcors.javaacademy.carlocation.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarLocation {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer carId;
    private Double latitude;
    private Double longitude;
}
