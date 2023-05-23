package ru.bmstu.loyaltyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LoyaltyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyAppApplication.class, args);
	}

}
