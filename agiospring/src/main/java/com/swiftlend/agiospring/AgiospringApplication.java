package com.swiftlend.agiospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AgiospringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgiospringApplication.class, args);
	}

}
