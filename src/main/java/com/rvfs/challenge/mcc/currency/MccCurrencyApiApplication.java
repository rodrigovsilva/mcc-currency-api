package com.rvfs.challenge.mcc.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MccCurrencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MccCurrencyApiApplication.class, args);
	}
}
