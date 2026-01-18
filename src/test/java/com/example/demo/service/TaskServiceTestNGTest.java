package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.testng.annotations.Test;  // ✅ TestNG import
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TaskServiceTestNGTest {
    @Test(expectedExceptions = ResourceNotFoundException.class)
    public void updateTask_throwsException_whenTaskNotFound(){
        TaskRepository repo = Mockito.mock(TaskRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        TaskService service = new TaskService(repo, userRepository);
        when(repo.findByTaskIdAndUserId("101", "Task-1")).thenReturn(java.util.Optional.empty());

        service.updateTaskById("Task-1", "101", "title", "desc");

    }
}
