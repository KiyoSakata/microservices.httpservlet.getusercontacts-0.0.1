package com.mycompany.config;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mycompany.controller.UserContactServlet;
import com.mycompany.controller.UserContactValidationFilter;

@Configuration
@ComponentScan("com.mycompany")
@EnableAutoConfiguration
public class GetUserContactApplication extends SpringBootServletInitializer {

	@Bean
	public Servlet dispatcherServlet() {
		return new UserContactServlet();
	}
	
	@Bean
	public Filter filter() {
		return new UserContactValidationFilter();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return mapper;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GetUserContactApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GetUserContactApplication.class, args);
	}
}
