package org.enrollapplication;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private String description;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    // Constructors
    public Course(){}

    public Course(String name, String description){
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public List<Student> getStudents(){
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
