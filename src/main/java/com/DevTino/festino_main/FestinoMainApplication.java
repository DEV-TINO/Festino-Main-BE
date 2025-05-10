package com.DevTino.festino_main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FestinoMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(FestinoMainApplication.class, args);
	}

}
//