package com.demeter.openiaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OpeniaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpeniaApiApplication.class, args);
	}

}
