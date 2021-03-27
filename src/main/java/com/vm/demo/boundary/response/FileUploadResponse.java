package com.vm.demo.boundary.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResponse {

    private String taskStatusPath;
    private String message;

    public FileUploadResponse(int taskStatusPathId, String message) {
        this.taskStatusPath = "http://localhost:8080/api/task/getStatus/"+taskStatusPathId;
        this.message = message;
    }

    public FileUploadResponse(String message) {
        this.message = message;
    }
}
