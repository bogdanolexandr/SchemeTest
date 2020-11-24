package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages="com")
@EntityScan("com.entity")
@EnableJpaRepositories("com.repository")
public class PsychologyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsychologyApplication.class, args);
	}

}
