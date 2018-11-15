package org.fkjava.cb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("org.fkjava")
@EnableJpaRepositories
public class CallBoradConfig {

	public static void main(String[] args) {
		SpringApplication.run(CallBoradConfig.class, args);
	}
}
