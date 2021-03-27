package com.vm.demo.controller;

import com.vm.demo.boundary.response.FileUploadResponse;
import com.vm.demo.boundary.helper.EmployeeFileHelper;
import com.vm.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = {"/upload"})
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String message = " ";
        if(EmployeeFileHelper.hasCSVFormat(multipartFile)) {
            try {
                employeeService.saveFile(multipartFile);
                message = "Uploaded the file successfully: " + multipartFile.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new FileUploadResponse(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + multipartFile.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileUploadResponse(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileUploadResponse(message));

    }
}
