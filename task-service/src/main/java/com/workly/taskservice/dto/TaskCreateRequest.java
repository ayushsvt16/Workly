package com.workly.taskservice.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskCreateRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
