package com.places.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlacesApplication {

	private static final Logger logger = LoggerFactory
			.getLogger(PlacesApplication.class);

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		// Mensaje de prueba
		logger.info("Starting Demo Application...");
		SpringApplication.run(PlacesApplication.class, args);
	}

}
