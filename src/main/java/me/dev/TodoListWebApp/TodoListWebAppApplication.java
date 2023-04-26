package me.dev.TodoListWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "me.dev.TodoListWebApp")
public class TodoListWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListWebAppApplication.class, args);
	}

}
