package com.apiscrum.APIScrum;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ApiScrumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiScrumApplication.class, args);
	}

}
