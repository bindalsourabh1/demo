package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UpdateTaskRequest {
    private String userId;
    private String taskId;
    private String title;
    private String description;
}
