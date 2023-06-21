package org.enrollapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }

    // GET endpoint to retrieve all enrollments
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    /**
     *
     * @param courseId
     * @param studentId
     * @return
     */
    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@RequestParam("courseId") Long courseId, @RequestParam("studentId") Long studentId){
        try {
            enrollmentService.enrollStudent(courseId, studentId);
            return ResponseEntity.ok("Student enrolled successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id){
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(enrollment);
    }

    /**
     *
     * @param id
     * @param updatedEnrollment
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id, @RequestBody Enrollment updatedEnrollment){
        Enrollment enrollment = enrollmentService.updateEnrollment(id, updatedEnrollment);
        return ResponseEntity.ok(enrollment);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id){
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
