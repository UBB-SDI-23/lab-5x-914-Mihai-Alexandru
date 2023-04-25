package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.dto.CourseByAvgProfSalaryDto;
import dev.alexmihai.universitycourses.dto.CourseGetAllDto;
import dev.alexmihai.universitycourses.dto.CourseGetByIdDto;
import dev.alexmihai.universitycourses.dto.CourseRequest;
import dev.alexmihai.universitycourses.exception.EntityNotFoundException;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.StudentCourse;
import dev.alexmihai.universitycourses.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")  // allow requests from any origin (for development purposes) - this is not recommended for production
@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseService service;

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody @Valid CourseRequest courseRequest) throws EntityNotFoundException {
        return new ResponseEntity<>(service.saveCourse(courseRequest), HttpStatus.CREATED);
    }

    @PostMapping("/courses/{courseId}/students")
    public ResponseEntity<Course> addCourseStudent(@PathVariable int courseId, @RequestBody StudentCourse studentCourses) throws EntityNotFoundException {
        return new ResponseEntity<>(service.addCourseStudent(courseId, studentCourses), HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseGetAllDto>> findAllCourses() throws EntityNotFoundException {
        return ResponseEntity.ok(service.getCourses());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseGetByIdDto> findCourseById(@PathVariable int id) throws EntityNotFoundException {
        return ResponseEntity.ok(service.getCourseById(id));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody @Valid CourseRequest courseRequest) throws EntityNotFoundException {
        return ResponseEntity.ok(service.updateCourse(id, courseRequest));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) throws EntityNotFoundException {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses/stats-courses-by-avg-prof-salary-desc")
    public ResponseEntity<List<CourseByAvgProfSalaryDto>> getCoursesByAvgProfSalaryDesc() throws EntityNotFoundException {
        return ResponseEntity.ok(service.getCoursesByAvgProfSalaryDesc());
    }

    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Void> deleteCourseStudent(@PathVariable int courseId, @PathVariable int studentId) throws EntityNotFoundException {
        service.deleteCourseStudent(courseId, studentId);
        return ResponseEntity.noContent().build();
    }
}
