package com.example_fbf.demo_fbf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DemoFbfApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFbfApplication.class, args);
	}

}
