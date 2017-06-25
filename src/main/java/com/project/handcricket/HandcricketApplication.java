package com.project.handcricket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HandcricketApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandcricketApplication.class, args);
	}

}
