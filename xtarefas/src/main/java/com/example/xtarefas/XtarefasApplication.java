package com.example.xtarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class XtarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtarefasApplication.class, args);
	}

}
