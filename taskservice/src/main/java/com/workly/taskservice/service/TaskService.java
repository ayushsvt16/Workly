package com.workly.taskservice.service;

import com.workly.taskservice.model.Task;
import com.workly.taskservice.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task create(String title, String description) {
        Task task = new Task(title, description);
        return repo.save(task);
    }

    public List<Task> getAll() {
        return repo.findAll();
    }

    public Task getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
