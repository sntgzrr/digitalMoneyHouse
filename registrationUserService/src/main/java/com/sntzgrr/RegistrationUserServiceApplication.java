package com.sntzgrr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RegistrationUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationUserServiceApplication.class, args);
	}

}
