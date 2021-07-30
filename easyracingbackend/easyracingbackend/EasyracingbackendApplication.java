package com.example.easyracing.easyracingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;

@EnableJpaRepositories(basePackages = "com.example.easyracing.easyracingbackend.Repository" )
@SpringBootApplication
public class EasyracingbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyracingbackendApplication.class, args);


	}


}
