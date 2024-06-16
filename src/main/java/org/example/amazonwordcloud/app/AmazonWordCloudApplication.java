package org.example.amazonwordcloud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AmazonWordCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazonWordCloudApplication.class, args);
	}

}
