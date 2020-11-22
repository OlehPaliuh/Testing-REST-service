package com.example.calculator;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Log4j2
public class CalculatorApplicationTests {

	public static final int PORT = 8080;

	@Test
	void contextLoads() {
	}

	public static String createURLWithPort(String uri) {
		return "http://localhost:" + CalculatorApplicationTests.PORT + uri;
	}

}
