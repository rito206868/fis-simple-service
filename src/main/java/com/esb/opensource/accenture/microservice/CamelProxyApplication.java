package com.esb.opensource.accenture.microservice;

import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class CamelProxyApplication extends SpringBootServletInitializer{
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(CamelProxyApplication.class);
}

public static void main(String[] args) {
	SpringApplication.run(CamelProxyApplication.class, args);
}

@Bean
public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception{
	SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
	camelContext.addRoutes(new ServiceCamelRoute());
	return camelContext;
	
}}
