package org.enrollapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnrollmentService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(CourseRepository courseRepository, StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // Retrieve all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    /**
     * Enroll a student in a course
     *
     * @param courseId  the ID of the course to enroll in
     * @param studentId the ID of the student to enroll
     */
    public void enrollStudent(Long courseId, Long studentId) {
        // Find course by ID
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + courseId));

        // Find student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + studentId));

        // Check if student is already enrolled in the course
        if (isEnrolled(course, student)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student is already enrolled in the course");
        }

        // Create a new enrollment
        Enrollment enrollment = new Enrollment(student, course);

        // Save the enrollment
        enrollmentRepository.save(enrollment);
    }

    /**
     * Get enrollment by ID
     *
     * @param id the ID of the enrollment
     * @return the enrollment with the specified ID
     */
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found with id: " + id));
    }

    /**
     * Update an enrollment
     *
     * @param id               the ID of the enrollment to update
     * @param updatedEnrollment the updated enrollment data
     * @return the updated enrollment
     */
    public Enrollment updateEnrollment(Long id, Enrollment updatedEnrollment) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found with id: " + id));

        // Update the enrollment with the new values
        enrollment.setStudent(updatedEnrollment.getStudent());
        enrollment.setCourse(updatedEnrollment.getCourse());

        // Save the updated enrollment
        return enrollmentRepository.save(enrollment);
    }

    /**
     * Delete an enrollment
     *
     * @param id the ID of the enrollment to delete
     */
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found with id: " + id));

        enrollmentRepository.delete(enrollment);
    }

    /**
     * Check if a student is enrolled in a course
     *
     * @param course  the course to check
     * @param student the student to check
     * @return true if the student is enrolled in the course, false otherwise
     */
    public boolean isEnrolled(Course course, Student student) {
        // Check if the student is enrolled in the course
        return course.getEnrollments().contains(student);
    }
}
