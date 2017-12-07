package com.dev.ewt.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PocConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocConfigServerApplication.class, args);
	}
}
