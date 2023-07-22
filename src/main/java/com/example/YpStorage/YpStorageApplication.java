package com.example.YpStorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class YpStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(YpStorageApplication.class, args);
	}

}
