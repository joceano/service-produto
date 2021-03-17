package com.joceano.serviceproduto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceProdutoApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProdutoApplication.class, args);
	}
}
