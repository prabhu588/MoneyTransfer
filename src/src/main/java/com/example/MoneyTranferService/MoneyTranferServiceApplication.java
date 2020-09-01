package com.example.MoneyTranferService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MoneyTranferServiceApplication {

	public RestTemplate getRestTemplate()
	{
	return new RestTemplate();	
	}
	public static void main(String[] args) {
		SpringApplication.run(MoneyTranferServiceApplication.class, args);
	}

}
