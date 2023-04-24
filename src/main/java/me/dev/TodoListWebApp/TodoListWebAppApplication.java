package me.dev.TodoListWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TodoListWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListWebAppApplication.class, args);
	}

}
