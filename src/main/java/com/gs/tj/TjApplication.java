package com.gs.tj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main application class for the Travel Journal application.
 * This class serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
@EnableCaching
public class TjApplication {

	public static void main(String[] args) {
		SpringApplication.run(TjApplication.class, args);
	}

}
