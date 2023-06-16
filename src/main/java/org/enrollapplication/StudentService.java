package org.enrollapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    /**
     * Constructor for StudentService Class
     *
     * @param studentRepository The StudentRepository instance to be injected
     */
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    /**
     * Get all students
     *
     * @return List of all students
     */
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    /**
     * Get Student by Id
     *
     * @param id The Id of the student to retrieve
     * @return Student with specified Id
     * @throws ResponseStatusException If student with given Id is not found
     */
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + id));
    }

    /**
     * Create a Student
     *
     * @param student The student object to create
     * @return The created student
     * @throws ResponseStatusException if student's email is either empty, invalid, or already existing or if name is empty.
     */
    public Student createStudent(Student student){
        // Perform additional validation or business logic here

        // Check if student's name is empty
        if (student.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty");
        }

        // Check if email is empty or not valid format
        if (student.getEmail().isEmpty() || !isValidEmail(student.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format");

        }

        // Check if student with the same email already exits
        if (studentRepository.findByEmail(student.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A student with the same email already exists");

        }

        // All validations passed, save the student
        return studentRepository.save(student);
    }

    /**
     * Helper class to validate email
     *
     * @param email The email to validate
     * @return True if email is in valid format, false otherwise
     */
    private boolean isValidEmail(String email){
        // Regular expression pattern for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compile the pattern into a regex object
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the email against the regex pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }
}
