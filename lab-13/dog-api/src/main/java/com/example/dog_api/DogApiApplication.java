package com.example.dog_api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class DogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogApiApplication.class, args);
		log.info("Woof! Woof!");
	}

}
