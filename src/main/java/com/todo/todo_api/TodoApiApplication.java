package com.todo.todo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "To-Do List API",
				version = "1.0.0",
				description = "This is a simple CRUD API for managing tasks"
		)
)
public class TodoApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(TodoApiApplication.class, args);
	}

}
