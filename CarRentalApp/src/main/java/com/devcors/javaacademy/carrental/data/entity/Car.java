package com.devcors.javaacademy.carrental.data.entity;

import com.devcors.javaacademy.carrental.data.entity.enums.CarColor;
import com.devcors.javaacademy.carrental.data.entity.enums.CarType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "manufactured")
    private Short year;

    private String brand;

    private String licencePlate;

    private CarType type;

    private CarColor color;

}
