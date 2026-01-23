package com.todo.todo_api.repository;

import com.todo.todo_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom method to find tasks based on completion status
    // Spring Data JPA automatically implements this based on the method name
    List<Task> findByCompleted(boolean completed);
}