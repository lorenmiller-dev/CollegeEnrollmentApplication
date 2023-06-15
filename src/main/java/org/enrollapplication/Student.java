package org.enrollapplication;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();


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
        this.email = email;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments){
        // set courses associated with student
        this.enrollments = enrollments;
    }
}
