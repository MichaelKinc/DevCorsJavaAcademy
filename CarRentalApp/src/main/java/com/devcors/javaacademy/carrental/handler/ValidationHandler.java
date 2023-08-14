package com.devcors.javaacademy.carrental.handler;

import com.devcors.javaacademy.carrental.data.ErrorResponse;
import com.devcors.javaacademy.carrental.exception.BorrowingNotFoundException;
import com.devcors.javaacademy.carrental.exception.CarAlreadyBorrowedException;
import com.devcors.javaacademy.carrental.exception.CarNotFoundException;
import com.devcors.javaacademy.carrental.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError objectError : objectErrors) {
            String name = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(name, errorMessage);
        }
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("method", ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors));
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("userNotFound", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), errors));
    }

    @ExceptionHandler(value = {CarNotFoundException.class})
    protected ResponseEntity<Object> handleCarNotFoundException(CarNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("carNotFound", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), errors));
    }

    @ExceptionHandler(value = {BorrowingNotFoundException.class})
    protected ResponseEntity<Object> handleBorrowingNotFoundException(BorrowingNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("borrowingNotFound", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), errors));
    }

    @ExceptionHandler(value = {CarAlreadyBorrowedException.class})
    protected ResponseEntity<Object> handleCarAlreadyBorrowedException(CarAlreadyBorrowedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("carAlreadyBorrowed", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), errors));
    }

}
