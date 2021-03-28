package com.vm.demo.boundary;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "TaskId")
    private int taskId;

    @Column(name = "TaskName")
    private String taskName;

    @Column(name = "taskStatus")
    private String taskStatus;

    public Task(String taskname, String taskStatus) {
        this.taskName = taskname;
        this.taskStatus = taskStatus;
    }
}
