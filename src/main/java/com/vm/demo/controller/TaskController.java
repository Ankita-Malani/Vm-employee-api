package com.vm.demo.controller;


import com.vm.demo.boundary.Task;
import com.vm.demo.boundary.response.TaskStatusResponse;
import com.vm.demo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/getStatus/{taskId}")
    public ResponseEntity<TaskStatusResponse> getTaskStatus(@PathVariable("taskId") String taskId) {
        Task task = taskService.fetchTask(Integer.parseInt(taskId));
        return ResponseEntity.status(HttpStatus.OK).body(new TaskStatusResponse(Integer.parseInt(taskId), task.getTaskStatus()));
    }
}
