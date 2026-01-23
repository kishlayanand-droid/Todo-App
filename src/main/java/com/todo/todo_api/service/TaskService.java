package com.todo.todo_api.service;

import com.todo.todo_api.dto.TaskRequestDTO;
import com.todo.todo_api.dto.TaskResponseDTO;
import com.todo.todo_api.exception.ResourceNotFoundException; // Import your exception
import com.todo.todo_api.model.Task;
import com.todo.todo_api.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. Create Task (DTO in -> DTO out)
    public TaskResponseDTO createTask(TaskRequestDTO request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(false);

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    // 2. Get All Tasks (Returns List of DTOs)
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 3. Filter by Status (Returns List of DTOs)
    public List<TaskResponseDTO> getTasksByStatus(boolean completed) {
        List<Task> tasks = taskRepository.findByCompleted(completed);
        return tasks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 4. Update Task Status (Returns DTO)
    public TaskResponseDTO updateTaskStatus(Long id, boolean completed) {
        // Find the task or throw our custom exception
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found."));

        // Update logic
        task.setCompleted(completed);

        // Save and convert back to DTO
        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    // 5. Delete Task
    public void deleteTask(Long id) {
        // Check existence first
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Task with ID " + id + " not found.");
        }
        taskRepository.deleteById(id);
    }

    // Helper method to convert Entity -> DTO
    private TaskResponseDTO mapToResponse(Task task) {
        TaskResponseDTO response = new TaskResponseDTO();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.isCompleted());
        return response;
    }
}
