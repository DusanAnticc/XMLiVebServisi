package com.spring.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class RestSluzbenikApplication {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RestSluzbenikApplication.class);
		app.run();
	}

}
