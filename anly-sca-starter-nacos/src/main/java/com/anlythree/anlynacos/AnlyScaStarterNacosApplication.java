package com.anlythree.anlynacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class AnlyScaStarterNacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnlyScaStarterNacosApplication.class, args);
	}

}
