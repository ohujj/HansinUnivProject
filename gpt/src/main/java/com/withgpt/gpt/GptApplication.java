package com.withgpt.gpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GptApplication {

	public static void main(String[] args) {
		SpringApplication.run(GptApplication.class, args);
	}

}
