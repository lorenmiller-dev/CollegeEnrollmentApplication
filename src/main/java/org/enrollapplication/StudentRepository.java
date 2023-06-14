package org.enrollapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Query Methods

    // finds all students in given course
    List<Student> findByCoursesIn(List<Course> courses);

    // find student by Id
    Optional<Student> findById(Long Id);

    // find all students
    List<Student> findAll();

    // find student by name
    List<Student> findByName(String name);

    // find students by email
    Optional<Student> findByEmail(String email);



}
