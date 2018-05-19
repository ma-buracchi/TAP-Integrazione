package it.buracchi.ciphers.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TapWebIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TapWebIntegrationApplication.class, args);
	}
}
