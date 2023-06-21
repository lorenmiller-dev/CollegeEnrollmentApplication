package org.enrollapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Query Methods

    // find all courses
    List<Course> findAll();

    // find course by Id
    Optional<Course> findById(Long Id);

    // find course by name
    List<Course> findByName(String name);

    // find courses in which specific student is enrolled
    List<Course> findByStudentsIn(List<Student> students);
}
