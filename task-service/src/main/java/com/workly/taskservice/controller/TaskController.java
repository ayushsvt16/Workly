package com.workly.taskservice.controller;

import com.workly.taskservice.dto.TaskCreateRequest;
import com.workly.taskservice.dto.TaskResponse;
import com.workly.taskservice.model.Task;
import com.workly.taskservice.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

@PostMapping
public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskCreateRequest request) {

    Task task = service.create(request.getTitle(), request.getDescription());

    TaskResponse response = new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getCreatedAt()
    );

    return ResponseEntity.ok(response);
}


    @GetMapping
    public List<TaskResponse> getAll() {
        return service.getAll().stream().map(task->new TaskResponse(
            task.getId(), 
            task.getTitle(),
            task.getDescription(),
            task.getCreatedAt()
        )).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        Task task=service.getById(id);
        TaskResponse response=new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getCreatedAt());

        return ResponseEntity.ok(response);
    }
}
