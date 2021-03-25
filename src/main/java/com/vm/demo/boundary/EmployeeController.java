package com.vm.demo.boundary;

import com.vm.demo.boundary.helper.EmployeeFileHelper;
import com.vm.demo.domain.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = {"/upload"})
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        EmployeeFileHelper.hasCSVFormat(multipartFile);
        employeeService.saveFile(multipartFile);
        return null;
    }
}
