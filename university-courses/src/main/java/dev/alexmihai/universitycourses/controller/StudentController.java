package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.dto.StudentGetAllDto;
import dev.alexmihai.universitycourses.dto.StudentGetByIdDto;
import dev.alexmihai.universitycourses.dto.StudentRequest;
import dev.alexmihai.universitycourses.exception.EntityNotFoundException;
import dev.alexmihai.universitycourses.model.Student;
import dev.alexmihai.universitycourses.model.StudentCourse;
import dev.alexmihai.universitycourses.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return new ResponseEntity<>(service.saveStudent(studentRequest), HttpStatus.CREATED);
    }

    @PostMapping("/{studentId}/courses")
    public ResponseEntity<Student> addStudentCourse(@PathVariable int studentId, @RequestBody StudentCourse studentCourses) throws EntityNotFoundException {
        return new ResponseEntity<>(service.addStudentCourse(studentId, studentCourses), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentGetAllDto>> findAllStudents() throws EntityNotFoundException {
        return ResponseEntity.ok(service.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentGetByIdDto> findStudentById(@PathVariable int id) throws EntityNotFoundException {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody @Valid StudentRequest studentRequest) throws EntityNotFoundException {
        return ResponseEntity.ok(service.updateStudent(id, studentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) throws EntityNotFoundException {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> deleteStudentCourse(@PathVariable int studentId, @PathVariable int courseId) throws EntityNotFoundException {
        service.deleteStudentCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
}
