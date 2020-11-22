package com.example.calculator.exception.controller;

import com.example.calculator.exception.InvalidInputValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidInputValueController {
    @ExceptionHandler(value = InvalidInputValue.class)
    public ResponseEntity<Object> exception(InvalidInputValue exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
