package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		Object dataSource = context.getBean("dataSource");
		System.out.println(dataSource);
	}
}
