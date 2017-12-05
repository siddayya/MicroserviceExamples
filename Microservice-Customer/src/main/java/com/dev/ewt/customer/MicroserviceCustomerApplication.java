package com.dev.ewt.customer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.dev.ewt.customer.entity.CustomerEntity;
import com.dev.ewt.customer.repository.CustomerRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceCustomerApplication {

	private final CustomerRepository customerRepository;

	@Autowired
	public MicroserviceCustomerApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@PostConstruct
	public void generateTestData() {
		customerRepository.save(new CustomerEntity("John", "Snow",
				"John.Snow@gmail.com", "Game of thrones ", "season6"));
		customerRepository.save(new CustomerEntity("Arya", "Stark",
				"arys.stark@got.com", "Game of Thrones", "season 7"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCustomerApplication.class, args);
	}
}
