package com.e2e.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This is the main class of the application
 * 
 * @author Abirami Silambarasan
 * 
 */

@SpringBootApplication
public class RestApiApp extends SpringBootServletInitializer {

	/**
	 * Main method to run application
	 * 
	 * @param arg[]
	 *            array to recieve input
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestApiApp.class, args);
	}

}
