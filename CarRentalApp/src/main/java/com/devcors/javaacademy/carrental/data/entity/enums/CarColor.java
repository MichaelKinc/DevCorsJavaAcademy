package com.devcors.javaacademy.carrental.data.entity.enums;

public enum CarColor {

    BLACK("BLACK"), WHITE("WHITE"), RED("RED"), GREEN("GREEN"), PINK("PINK");

    private final String carColorName;

    CarColor(String carColorName) {
        this.carColorName = carColorName;
    }

    public String getCarColorName() {
        return carColorName;
    }

}