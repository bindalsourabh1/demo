package com.example.demo.controller;

import com.example.demo.controller.TaskController;
import com.example.demo.dto.TaskDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void getTaskById_returns404_whenTaskNotFound() throws Exception {

        when(taskService.getTaskByIdAndUserId("101", "Task-1"))
                .thenThrow(new ResourceNotFoundException("Task not found"));

        mockMvc.perform(get("/tasks/101/Task-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTaskById_returns200_whenTaskExists() throws Exception{
        TaskDto dto = new TaskDto();
        dto.setTaskId("Task1");
        dto.setTitle("Learn Spring");
        dto.setDescription("Understand MVC issues");
        when(taskService.getTaskByIdAndUserId("101", "Task1")).
                thenReturn(dto);
        mockMvc.perform(get("/tasks/101/Task1"))
                .andExpect(status().isOk());
    }
}
