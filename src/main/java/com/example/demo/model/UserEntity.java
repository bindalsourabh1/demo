package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.LocalDateTime;

@Document @Data @AllArgsConstructor @NoArgsConstructor
public class UserEntity {
    @Id
    private String Id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
