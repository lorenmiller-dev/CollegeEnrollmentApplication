package org.enrollapplication;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int age;
    private String address;
    private String gender;
    // Fields represent attributes of a student

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    // Constructors

    public Student() {
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

    public int getAge() {
        // get age of student
        return age;
    }

    public void setAge(int age) {
        // set age of student
        this.age = age;
    }

    public String getAddress(){
        // get address of student
        return address;
    }

    public void setAddress(String address) {
        // set address of student
        this.address = address;
    }

    public String getGender(){
        // get sex of student
        return gender;
    }

    public void setGender(String gender) {
        // set gender of student
        this.gender = gender;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments){
        // set courses associated with student
        this.enrollments = enrollments;
    }
}
