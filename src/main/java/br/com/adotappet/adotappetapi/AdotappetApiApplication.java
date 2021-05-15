package br.com.adotappet.adotappetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AdotappetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdotappetApiApplication.class, args);
	}

}
