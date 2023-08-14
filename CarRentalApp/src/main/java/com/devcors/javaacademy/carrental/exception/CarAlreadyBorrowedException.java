package com.devcors.javaacademy.carrental.exception;

public class CarAlreadyBorrowedException extends RuntimeException {
    public CarAlreadyBorrowedException(String message) {
        super(message);
    }
}
