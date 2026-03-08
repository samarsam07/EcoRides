package com.samar.ecoRides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EcoRidesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoRidesApplication.class, args);
	}

}
