package com.vm.demo.boundary.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskStatusResponse {

    private int taskId;
    private String taskStatus;

}
