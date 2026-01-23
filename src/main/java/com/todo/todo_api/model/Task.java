package com.todo.todo_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data // Lombok annotation to generate Getters, Setters, etc.
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required") // Validation
    private String title;

    private String description;

    private boolean completed = false; // Default status is pending
}