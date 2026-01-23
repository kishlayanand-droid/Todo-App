package com.todo.todo_api.controller;

import com.todo.todo_api.dto.TaskRequestDTO;
import com.todo.todo_api.dto.TaskResponseDTO;
import com.todo.todo_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller for handling Task operations
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. Create a Task
    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO task) {
        return taskService.createTask(task);
    }

    // 2. Get All Tasks
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    // 3. Filter by Status (Completed/Pending)
    // Usage: GET /api/tasks/filter?completed=true
    @GetMapping("/filter")
    public List<TaskResponseDTO> getTasksByStatus(@RequestParam boolean completed) {
        return taskService.getTasksByStatus(completed);
    }

    // 4. Update Task Status
    // Usage: PUT /api/tasks/1/status?completed=true
    @PutMapping("/{id}/status")
    public TaskResponseDTO updateStatus(@PathVariable Long id, @RequestParam boolean completed) {
        return taskService.updateTaskStatus(id, completed);
    }

    // 5. Delete a Task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}