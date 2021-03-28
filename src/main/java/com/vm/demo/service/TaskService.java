package com.vm.demo.service;

import com.vm.demo.boundary.Task;
import com.vm.demo.boundary.TaskStatus;
import com.vm.demo.jpa.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task initiateRunningTask(String taskName) {
        Task task = new Task(taskName, TaskStatus.PROCESSING.name());
        try {
            Task taskDbResponse = taskRepository.save(task);
            return taskDbResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new Task();
        }
    }

    public Task markFailedTask(int taskId) {
        try {
            Optional<Task> task = taskRepository.findById(taskId);
            if (task.isPresent()) {
                task.get().setTaskStatus(TaskStatus.FAILED.name());
                taskRepository.save(task.get());
            }
            return task.get();
       } catch (Exception e) {
            e.printStackTrace();
       }
        return new Task();
    }

    public Task markSuccessfulTask(int taskId) {
        try {
            Optional<Task> task = taskRepository.findById(taskId);
            if(task.isPresent()) {
                task.get().setTaskStatus(TaskStatus.SUCCESS.name());
                taskRepository.save(task.get());
            }
            return task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Task();
    }

    public Task fetchTask(int taskId) {
        try {
            return taskRepository.findById(taskId).get();
        } catch(Exception e) {
            e.printStackTrace();
            return new Task();
        }
    }
}
