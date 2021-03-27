package com.vm.demo.boundary;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    public Employee( String name, int age) {
       // this.id = id;
        this.name = name;
        this.age = age;
    }


}
