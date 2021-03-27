package com.vm.demo.controller;

import com.vm.demo.boundary.Employee;
import com.vm.demo.boundary.Task;
import com.vm.demo.boundary.response.FileUploadResponse;
import com.vm.demo.boundary.helper.EmployeeFileHelper;
import com.vm.demo.service.EmployeeService;
import com.vm.demo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final TaskService taskService;

    private static final String FILE_UPLOAD_TASKNAME = "FileUpload";

    @PostMapping(value = {"/upload"})
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String message = " ";
        Task task = new Task();

        if(EmployeeFileHelper.hasCSVFormat(multipartFile) && !multipartFile.isEmpty()) {
            try {
                task = taskService.initiateRunningTask(FILE_UPLOAD_TASKNAME);
                List<Employee> employeeList = EmployeeFileHelper.convertCsvToEmployee(multipartFile.getInputStream());
                employeeService.saveFile(employeeList);
                taskService.markSuccessfulTask(task.getTaskId());
                message = "Task "+task.getTaskId() + " is in " + task.getTaskStatus() + " State, Please try hitting above taskStatusPath to know more about task";
                return ResponseEntity.status(HttpStatus.OK).body(new FileUploadResponse(task.getTaskId(), message));

            } catch (Exception e) {
                task = taskService.markFailedTask(task.getTaskId());
                message = "Task + " + task.getTaskId()+ " for file upload has been failed, please check logs";
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileUploadResponse(task.getTaskId(), message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileUploadResponse(message));

    }
}
