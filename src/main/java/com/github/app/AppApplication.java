package com.github.app;
//A anotação @SpringBootApplication estava sendo usada sem o import correto. Os imports presentes (ComponentScan e Configuration) não são necessários e não substituem o import da anotação principal.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}