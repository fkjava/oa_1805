package org.fkjava.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.fkjava")
public class StorageConfig {

	public static void main(String[] args) {
		SpringApplication.run(StorageConfig.class, args);
	}
}
