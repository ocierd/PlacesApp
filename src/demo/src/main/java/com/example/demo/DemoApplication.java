package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoApplication.class);

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		// Mensaje de prueba
		logger.info("Starting Demo Application...");
		SpringApplication.run(DemoApplication.class, args);
	}

}
