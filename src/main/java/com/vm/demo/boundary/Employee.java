package com.vm.demo.boundary;

import lombok.Data;

@Data
public class Employee {

    int id;
    String name;
    int age;

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
