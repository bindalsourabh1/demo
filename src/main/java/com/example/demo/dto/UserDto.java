package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}

