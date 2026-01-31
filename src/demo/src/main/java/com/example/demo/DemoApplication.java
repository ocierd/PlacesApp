package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
/**
 * Main method to start the Spring Boot application.
 * @param args command line arguments
 */
	public static void main(String[] args) {
		// Mensaje de prueba
		System.out.println("Starting Demo Application...");
		SpringApplication.run(DemoApplication.class, args);
	}

}
