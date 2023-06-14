package org.enrollapplication;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    // @Entity annotation indicates class is entity and will be mapped to database table

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // @Id annotation indicates field is primary key of the entity

    private String name;
    private String email;
    // Fields represent attributes of a student

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
        private List<Course> courses = new ArrayList<>();

    // Constructors
    public Student(){
    }

    public Student(String name, String email){
        // Parameterized constructor to create a Student object with name and email
        this.name = name;
        this.email = email;
    }

    // Getters and Setters

}
