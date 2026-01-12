package com.example.demo.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    String currEmail;
    String newEmail;
}
