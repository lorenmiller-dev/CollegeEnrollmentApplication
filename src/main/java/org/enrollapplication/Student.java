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
    // @ManyToMany annotation indicates a student can be associated with multiple courses
    // and course can be associated with multiple students
    // @JoinTable specifies details of the join table that will be used to store the relationship


    // Constructors
    public Student() {
    }

    public Student(String name, String email) {
        // Parameterized constructor to create a Student object with name and email
        this.name = name;
        this.email = email;
    }

    // Getters and Setters

    public long getId() {
        // retrieve id of student
        return id;
    }

    public String getName(){
        // retrieve name of student
        return name;
    }

    public void setName(String name){
        // set name of the student
        this.name = name;
    }

    public String getEmail(){
        // retrieve email of student
        return email;
    }

    public void setEmail(String email){
        this.email =email;
    }

    public void setCourses(List<Course> courses){
        // set courses associated with student
        this.courses = courses;
    }
}
