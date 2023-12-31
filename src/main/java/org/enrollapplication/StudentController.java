package org.enrollapplication;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    /**
     * Constructor for StudentController Class
     *
     * @param studentService the StudentService instance to be injected
     */
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * Get all students
     *
     * @return list of all students in StudentDTO format
     */
    @GetMapping
    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        List<StudentDTO> studentDTOs = new ArrayList<>();

        for (int i = 0; i < students.size(); i++){
            Student student = students.get(i);
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTOs.add(studentDTO);
        }

        return studentDTOs;
    }

    /**
     * Get student by Id
     *
     * @param id the Id of the student
     * @return student of the specified Id
     */
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    /**
     * Create new student
     *
     * @param student student object to create
     * @return Response entity containing the created student in the response body with HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student createdStudent = studentService.createStudent(student);
        // return created student in the response body with HTTP status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    /**
     *  Update a Student given specified Id
     *
     * @param id The Id of a student
     * @param updatedStudent the updated student
     * @return Response entity with the updated student in the response body with HTTP 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student updatedStudent){
        Student savedStudent = studentService.updateStudent(id, updatedStudent);
        return ResponseEntity.ok(savedStudent);
    }

    /**
     * Delete student
     *
     * @param id Id of student to delete
     * @return Response entity with an empty response body and HTTP status 204 (No content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        // return empty response body with HTTP status 204 (No content)
        return ResponseEntity.noContent().build();
    }
}
