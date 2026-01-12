package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.Task;
import com.example.demo.model.TaskEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskService {


    private final UserRepository userRepository;
    private final TaskRepository repo;
    public TaskService(TaskRepository repo, UserRepository userRepository) {
        this.repo = repo;
        this.userRepository = userRepository;
    }


    public UserDto createUser(String name, String email){
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserAlreadyExistsException(
                    "User with email " + email + " already exists"
            );
        });
        UserEntity user = new UserEntity();
        user.setId("user::" + UUID.randomUUID());
        user.setName(name);
        user.setEmail(email);
        user.setCreatedAt(LocalDateTime.now());
        UserEntity saved = userRepository.save(user);
        return mapToUserDto(saved);
    }

    public UserDto mapToUserDto(UserEntity user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setCreatedAt(user.getCreatedAt());
        return userDto;
    }


    public UserDto getUserById(String userId) {
        return userRepository.findById(userId)
                .map(this::mapToUserDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with this %s id is not found", userId)
                ));
    }







    public Task createTask(String userId, String title, String description){
        TaskEntity entity = new TaskEntity();
        entity.setId("task::" + UUID.randomUUID());
        entity.setUserId(userId);
        entity.setTaskId("Task-" + System.currentTimeMillis());
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setCompleted(false);
        entity.setCreatedAt(LocalDateTime.now());

        TaskEntity saved = repo.save(entity);
        return mapToApi(saved);
    }

    private Task mapToApi(TaskEntity e){
        Task t = new Task();
        t.setTaskId(e.getTaskId());
        t.setTitle(e.getTitle());
        t.setDescription(e.getDescription());
        t.setUserId(e.getUserId());
        t.setTaskId(e.getTaskId());
        return t;

    }



    private TaskDto mapToDto(TaskEntity e){
        TaskDto t = new TaskDto();
        t.setTaskId(e.getTaskId());
        t.setTitle(e.getTitle());
        t.setDescription(e.getDescription());
        t.setCompleted(e.isCompleted());
        t.setUserId(e.getUserId());
        t.setCreatedAt(e.getCreatedAt());
        return t;
    }

    public TaskDto getTaskByIdAndUserId(String userId, String taskId){
        return repo.findByTaskIdAndUserId(taskId, userId).map(this::mapToDto).orElse(null);
    }

    public List<TaskEntity> getAllTaskByUserId(String userId){
        return repo.findByUserId(userId);
    }

    public void deleteTaskById(String taskId, String userId){
        Optional<TaskEntity> t = repo.findByTaskIdAndUserId(taskId, userId);
        t.ifPresent(repo::delete);
    }

    //updating task
    public TaskDto updateTaskById(String userId, String taskId, String title, String description) {
        Optional<TaskEntity> optionalTaskEntity = repo.findByTaskIdAndUserId(taskId, userId);
        TaskEntity taskEntity = optionalTaskEntity.
                orElseThrow(() -> new ResourceNotFoundException(String.format("Task %s , User %s not found", taskId, userId)));

        taskEntity.setTitle(title);
        taskEntity.setDescription(description);
        TaskEntity saved = repo.save(taskEntity);
        return mapToDto(saved);
    }

    public UserDto updateUserEmail(String currEmail, String newEmail) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(currEmail);
        UserEntity userEntity = optionalUserEntity.
                orElseThrow(() -> new ResourceNotFoundException(String.format("Email id %s not found", currEmail)));
        userEntity.setEmail(newEmail);
        UserEntity saved = userRepository.save(userEntity);
        return mapToUserDto(saved);
    }

//    private final Map<String, Map<String, Task>> userTask = new ConcurrentHashMap<>();
//    private long taskIdCounter = 1;
//
//
//    /*
//    userTask hashmap
//    userId -> {taskId-> task}
//     */
//
//
//    // CREATE creating a new task for the user
//    public Task createTask(String userId, String title, String description){
//        Task task = new Task();
//        task.setUserId(userId);
//        task.setTaskId("Task-" + taskIdCounter);
//        task.setTitle(title);
//        task.setDescription(description);
//        task.setCompleted(false);
//        task.setCreatedAt(LocalDateTime.now());
//
//        userTask.putIfAbsent(userId, new ConcurrentHashMap<>());
//        userTask.get(userId).put(task.getTaskId(), task);
//        taskIdCounter++;
//        return task;
//    }
//
//    // getting all the tasks for the user
//    public List<Task> getAllTaskByUserId(String userId){
//        Map<String, Task> tasks = userTask.get(userId);
//        if(tasks == null){
//            return null;
//        }
//        return new ArrayList<>(tasks.values());
//    }
//
//    //GET
//    //getting a task by its Id
//    public Task getTaskById(String userId, String taskId){
//        if(userTask.containsKey(userId)){
//            return userTask.get(userId).get(taskId);
//        }
//        return null;
//    }
//
//    //DELETE
//    //delete a task by its Id
//    public void deleteTaskById(String userId, String taskId){
//        if(userTask.containsKey(userId)){
//            userTask.get(userId).remove(taskId);
//        }
//    }
//
//    //UPDATE
//    // update a task by its id
//    public Task updateTaskById(String userId, String taskId, String title, String description, Boolean completed){
//        if(userTask.containsKey(userId)){
//            Task task = userTask.get(userId).get(taskId);
//            if(task != null){
//                task.setTitle(title);
//                task.setDescription(description);
//                task.setCompleted(completed);
//                return task;
//            }
//        }
//        return null;
//    }

}
