package com.icesi.microservicio_miembro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicioMiembroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioMiembroApplication.class, args);
	}

}
