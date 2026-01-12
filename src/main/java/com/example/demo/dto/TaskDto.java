package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TaskDto {
    private String taskId;
    private String userId;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
}
