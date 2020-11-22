package com.example.calculator.exception;

public class InvalidInputValue extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidInputValue(String s) {
        super(s);
    }
}
