package com.devcors.javaacademy.carrental.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    private final int status;
    private final String message;
    private final Map<String, String> errors;
}
