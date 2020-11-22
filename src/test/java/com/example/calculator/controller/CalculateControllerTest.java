package com.example.calculator.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static com.example.calculator.CalculatorApplicationTests.createURLWithPort;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
public class CalculateControllerTest {

    public CalculateControllerTest() {
    }

    private static Stream<Arguments> sumDataProvider() {
        return Stream.of(
                Arguments.of(1.0, 1.0, 2.0),
                Arguments.of(11.22222, 2.2, 13.4222),
                Arguments.of(8.2, 1.2, 9.4),
                Arguments.of(1d, 6d, 7d),
                Arguments.of(1d, 1d, 2d)
        );
    }

    private static Stream<Arguments> difDataProvider() {
        return Stream.of(
                Arguments.of(3.0, 1.0, 2.0),
                Arguments.of(11.22222, 1.2, 10.0222),
                Arguments.of(8.2, 1.2, 7.0),
                Arguments.of(1d, 6d, -5d),
                Arguments.of(1d, 1d, 0d)
        );
    }

    private static Stream<Arguments> divDataProvider() {
        return Stream.of(
                Arguments.of(3.0, 1.0, 3.0),
                Arguments.of(12.0, 1.2, 10.0),
                Arguments.of(8.2, 1.5, 5.4667),
                Arguments.of(1d, 6d, 0.1667),
                Arguments.of(1d, -1d, -1d)
        );
    }

    private static Stream<Arguments> divByZeroDataProvider() {
        return Stream.of(
                Arguments.of(3.0, 0.0),
                Arguments.of(12.0, 0.0),
                Arguments.of(8.2, 0.0),
                Arguments.of(-12.22, 0.0),
                Arguments.of(232.222222, 0.0)
        );
    }

    private static Stream<Arguments> multDataProvider() {
        return Stream.of(
                Arguments.of(3.0, 1.0, 3.0),
                Arguments.of(12.0, 1.2, 14.4),
                Arguments.of(9.1, 0.5, 4.55),
                Arguments.of(-1d, 6d, -6.0),
                Arguments.of(22.33333, 33.1123, 739.5079)
        );
    }

    private static Stream<Arguments> perDataProvider() {
        return Stream.of(
                Arguments.of(3.0, 0.03),
                Arguments.of(12.0, 0.12),
                Arguments.of(90d, 0.9),
                Arguments.of(-1d, -0.01),
                Arguments.of(22.33333, 0.2233)
        );
    }

    @ParameterizedTest
    @MethodSource("sumDataProvider")
    public void testSumOfValues(Double valueA, Double valueB, Double expectedResult) throws IOException {

        HttpUriRequest request = new HttpGet(createURLWithPort(
                String.format("/api/sum?a=%s&b=%s", valueA.toString(), valueB.toString())));
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseString = new BasicResponseHandler().handleResponse(response);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(responseString, String.valueOf(expectedResult));

        log.info("Sum of " + valueA + " and " + valueB + " -> Expected value : " +
                expectedResult + " Actual value " + responseString);
    }

    @ParameterizedTest
    @MethodSource("difDataProvider")
    public void testDifferanceOfValues(Double valueA, Double valueB, Double expectedResult) throws IOException {

        HttpUriRequest request = new HttpGet(createURLWithPort(
                String.format("/api/dif?a=%s&b=%s", valueA.toString(), valueB.toString())));
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseString = new BasicResponseHandler().handleResponse(response);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(responseString, String.valueOf(expectedResult));

        log.info("Differance of " + valueA + " and " + valueB + " -> Expected value : " +
                expectedResult + " Actual value " + responseString);
    }

    @ParameterizedTest
    @MethodSource("divDataProvider")
    public void testDivisionOfValues(Double valueA, Double valueB, Double expectedResult) throws IOException {

        HttpUriRequest request = new HttpGet(createURLWithPort(
                String.format("/api/div?a=%s&b=%s", valueA.toString(), valueB.toString())));
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseString = new BasicResponseHandler().handleResponse(response);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(responseString, String.valueOf(expectedResult));

        log.info("Division of " + valueA + " and " + valueB + " -> Expected value : " +
                expectedResult + " Actual value " + responseString);
    }

    @ParameterizedTest
    @MethodSource("divByZeroDataProvider")
    public void testDivisionByZero(Double valueA, Double valueB) throws IOException {

        HttpUriRequest request = new HttpGet(createURLWithPort(
                String.format("/api/div?a=%s&b=%s", valueA.toString(), valueB.toString())));
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, response.getStatusLine().getStatusCode());

        log.info("Status code: " + response.getStatusLine().getStatusCode());
    }

    @ParameterizedTest
    @MethodSource("multDataProvider")
    public void testMultiplicationOfValues(Double valueA, Double valueB, Double expectedResult) throws IOException {

        HttpUriRequest request = new HttpGet(createURLWithPort(
                String.format("/api/mult?a=%s&b=%s", valueA.toString(), valueB.toString())));
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseString = new BasicResponseHandler().handleResponse(response);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(responseString, String.valueOf(expectedResult));

        log.info("Multiplication of " + valueA + " and " + valueB + " -> Expected value : " +
                expectedResult + " Actual value " + responseString);
    }

    @ParameterizedTest
    @MethodSource("perDataProvider")
    public void testPercentageOfValues(Double valueA, Double expectedResult) throws IOException {

        HttpUriRequest request = new HttpGet(createURLWithPort(
                String.format("/api/per?a=%s", valueA.toString())));
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseString = new BasicResponseHandler().handleResponse(response);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(responseString, String.valueOf(expectedResult));

        log.info("Percentage of " + valueA + " -> Expected value : " +
                expectedResult + " Actual value " + responseString);
    }

}
