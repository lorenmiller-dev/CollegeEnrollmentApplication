package org.enrollapplication;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    /**
     * Constructs a new CourseService with the given CourseRepository.
     *
     * @param courseRepository the CourseRepository to be used
     */
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    /**
     * Get all courses
     *
     * @return List of all courses
     */
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    /**
     * Retrieve a course by its ID.
     *
     * @param id the ID of the course
     * @return the course with the specified ID
     * @throws ResponseStatusException if the course is not found
     */
    public Course getCourseById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id));
    }


    /**
     * Create a new course.
     *
     * @param course the course to be created
     * @return the created course
     * @throws ResponseStatusException if the course name is empty or a course with the same name already exists
     */
    public Course createCourse(Course course){
        // Perform additional validation or business logic

        // Check if course name is empty
        if (course.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course name cannot be empty");
        }

        // Check if course already exists
        List<Course> courses = courseRepository.findByName(course.getName());
        if (!courses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course with the same name already exists");
        }

        // Save created course to course repository
        return courseRepository.save(course);
    }

    /**
     * Update an existing course.
     *
     * @param id the ID of the course to be updated
     * @param updatedCourse the updated course object
     * @return the updated course
     * @throws ResponseStatusException if the course is not found
     */
    public Course updateCourse(Long id, Course updatedCourse){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id));

        // Update the course properties
        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());

        // Save the updated course
        return courseRepository.save(course);
    }

    /**
     * Delete a specified course given an ID.
     *
     * @param id the ID of the course to be deleted
     * @throws ResponseStatusException if the course is not found or has existing enrollments
     */
    public void deleteCourse(Long id){
        Course course =  courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id));

        // Check if there are any enrollments associated with the course
        if (!course.getEnrollments().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete a course with existing enrollments");
        }

        // delete course
        courseRepository.delete(course);
    }
}
