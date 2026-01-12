package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.LocalDateTime;

@Document
@Data @AllArgsConstructor @NoArgsConstructor
public class TaskEntity {
    @Id
    private String id;

    @Field
    private String taskId;

    @Field
    private String userId;

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private boolean completed;

    @Field
    private LocalDateTime createdAt;
}
