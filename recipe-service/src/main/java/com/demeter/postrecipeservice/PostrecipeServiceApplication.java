package com.demeter.postrecipeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PostrecipeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostrecipeServiceApplication.class, args);
	}

}
