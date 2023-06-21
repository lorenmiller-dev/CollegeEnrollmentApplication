package org.enrollapplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    /**
     * Get all courses
     *
     * @return list of all courses
     */
    @GetMapping
    public List<CourseDTO> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> courseDTOs = new ArrayList<>();

        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setName(course.getName());
            // Set other desired properties of the courseDTO

            courseDTOs.add(courseDTO);
        }

        return courseDTOs;
    }

    /**
     * Get a course by Id
     *
     * @param id The id of the course object
     * @return The course
     */
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    /**
     *
     * @param course
     * @return
     */
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course createdCourse = courseService.createCourse(course);

        // Return created course in response body with HTTP status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    /**
     * Update a course given a specified Id
     *
     * @param id
     * @param course
     * @return
     */
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course){
        return courseService.updateCourse(id, course);
    }

    /**
     * Delete a course
     * @param id ID of the course to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        // Return an empty response body with HTTP status 204 (No Content)
        return ResponseEntity.noContent().build();
    }
}
