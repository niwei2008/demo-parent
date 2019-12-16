package com.mixin.demo.chapter1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class Chapter1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter1Application.class, args);
	}

}
