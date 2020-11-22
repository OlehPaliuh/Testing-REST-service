package com.example.calculator.service;

import com.example.calculator.exception.InvalidInputValue;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    private static final int PRECISION = 10000;

    public Double addition(Double a, Double b) {
        return Math.floor((a + b) * PRECISION + .5) / PRECISION;
    }

    public Double subtraction(Double a, Double b) {
        return Math.floor((a - b) * PRECISION + .5) / PRECISION;
    }

    public Double division(Double a, Double b) {
        if (Double.valueOf(0.0).equals(b)) {
            throw new InvalidInputValue("Division by 0 is forbidden");
        }
        return Math.floor((a / b) * PRECISION + .5) / PRECISION;
    }

    public Double multiplication(Double a, Double b) {
        return Math.floor((a * b) * PRECISION + .5) / PRECISION;
    }

    public Double percentage(Double a) {
        return Math.floor((a * 0.01) * PRECISION + .5) / PRECISION;
    }
}
