package com.example.calculator.controller;

import com.example.calculator.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping(path = "/sum")
    public ResponseEntity<Double> sum(@RequestParam Double a, Double b) {
        return new ResponseEntity<>(calculationService.addition(a,b), HttpStatus.OK);
    }

    @GetMapping(path = "/dif")
    public ResponseEntity<Double> diff(Double a, Double b) {
        return new ResponseEntity<>(calculationService.subtraction(a,b), HttpStatus.OK);
    }

    @GetMapping(path = "/div")
    public ResponseEntity<Double> division(Double a, Double b) {
        return new ResponseEntity<>(calculationService.division(a,b), HttpStatus.OK);
    }

    @GetMapping(path = "/mult")
    public ResponseEntity<Double> multiplication(Double a, Double b) {
        return new ResponseEntity<>(calculationService.multiplication(a,b),  HttpStatus.OK);
    }

    @GetMapping(path = "/per")
    public ResponseEntity<Double> percentage(Double a) {
        return new ResponseEntity<>(calculationService.percentage(a), HttpStatus.OK);
    }
}
