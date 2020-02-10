package com.abeldevelop.architecture.service.management.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"${abeldevelop.configuration.component-scan}"})
@EntityScan("${abeldevelop.configuration.entity-scan}")
@EnableJpaRepositories("${abeldevelop.configuration.enable-jpa-repositories}")
@EnableDiscoveryClient
@EnableFeignClients({"${abeldevelop.configuration.component-scan}"})
@SpringBootApplication
public class MainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainServiceApplication.class, args);
	}

}
