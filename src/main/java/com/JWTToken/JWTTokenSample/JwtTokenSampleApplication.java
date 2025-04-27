package com.JWTToken.JWTTokenSample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.JWTToken.JWTTokenSample.entity"})  
@ComponentScan(basePackages = {"com.JWTToken.JWTTokenSample","com.JWTToken.JWTTokenSample.service", "com.JWTToken.JWTTokenSample.repo"})
public class JwtTokenSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokenSampleApplication.class, args);
	}

}
