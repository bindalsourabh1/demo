package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Task;
import com.example.demo.model.TaskEntity;
import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    //DI constructor
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    //method for creating user
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest user){
        UserDto created = taskService.createUser(user.getName(), user.getEmail());
        return ResponseEntity.status(201).body(created);
    }


    //Method for updating the user's name
    @PatchMapping("/updateEmail")
    public ResponseEntity<UserDto> updateUserEmail(@RequestBody UpdateUserRequest updateUser){
        UserDto userDto = taskService.updateUserEmail(updateUser.getCurrEmail(), updateUser.getNewEmail());
        return ResponseEntity.ok(userDto);
    }


    //method for finding user by ID
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId){
        log.info("User Id is this {}", userId);
        UserDto user = taskService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    //method for creating task by user
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest ut){
        Task created = taskService.createTask(ut.getUserId(), ut.getTitle(), ut.getDescription());
        return ResponseEntity.status(201).body(created);
    }


    //getting all task for the user from the userId
    @GetMapping("/{userId}")
    public ResponseEntity<List<TaskEntity>> getAllTaskUser(@PathVariable String userId){
        return ResponseEntity.status(200).body(taskService.getAllTaskByUserId(userId));
    }

    //getting a task by the ID
    @GetMapping("/{userId}/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable String userId, @PathVariable String taskId){
//        return ResponseEntity.status(200).body(taskService.getTaskByIdAndUserId(userId, taskId));
        TaskDto task = taskService.getTaskByIdAndUserId(userId, taskId);
        if(task != null){
            return ResponseEntity.status(200).body(task);
        }
        return ResponseEntity.notFound().build();
    }


    //updating a task from userId and taskId
    @PutMapping()
    public ResponseEntity<TaskDto> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest){
        TaskDto updatedTask = taskService.updateTaskById(updateTaskRequest.getUserId(), updateTaskRequest.getTaskId(), updateTaskRequest.getTitle(), updateTaskRequest.getDescription());
        return ResponseEntity.status(200).body(updatedTask);
    }




    //deleting a task from userId and taskId
    @DeleteMapping("/{userId}/{taskId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable String userId, @PathVariable String taskId){
        taskService.deleteTaskById(userId, taskId);
        return ResponseEntity.noContent().build();
    }

}
