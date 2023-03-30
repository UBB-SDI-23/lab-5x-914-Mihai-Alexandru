package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.*;
import dev.alexmihai.universitycourses.exception.EntityNotFoundException;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.model.Student;
import dev.alexmihai.universitycourses.model.StudentCourse;
import dev.alexmihai.universitycourses.repository.CourseRepository;
import dev.alexmihai.universitycourses.repository.ProfessorRepository;
import dev.alexmihai.universitycourses.repository.StudentRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Course saveCourse(CourseRequest courseRequest) throws EntityNotFoundException {
        Professor professor = professorRepository.findById(courseRequest.getProfessorId()).orElse(null);
        if (professor == null) {
            throw new EntityNotFoundException(
                    String.format("Professor with id %d does not exist!", courseRequest.getProfessorId()));
        }
        Course newCourse = new Course();
        newCourse.setNumberOfCredits(courseRequest.getNumberOfCredits());
        newCourse.setTitle(courseRequest.getTitle());
        newCourse.setDescription(courseRequest.getDescription());
        newCourse.setStartDate(courseRequest.getStartDate());
        newCourse.setEndDate(courseRequest.getEndDate());
        newCourse.setProfessor(professor);
        return courseRepository.save(newCourse);
    }

    public List<CourseGetAllDto> getCourses() throws EntityNotFoundException {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new EntityNotFoundException("No courses found!");
        }
        List<CourseGetAllDto> coursesDto = new ArrayList<>();
        for (Course course : courses) {
            CourseGetAllDto courseDto = new CourseGetAllDto(
                    course.getId(),
                    course.getNumberOfCredits(),
                    course.getTitle(),
                    course.getDescription(),
                    course.getStartDate(),
                    course.getEndDate(),
                    course.getProfessor().getId()
            );
            coursesDto.add(courseDto);
        }
        return coursesDto;
    }

    public CourseGetByIdDto getCourseById(int id) throws EntityNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new EntityNotFoundException(String.format("Course with id %d does not exist!", id));
        }
        return new CourseGetByIdDto(
                course.getId(),
                course.getNumberOfCredits(),
                course.getTitle(),
                course.getDescription(),
                course.getStartDate(),
                course.getEndDate(),
                ObjectMapperUtils.map(course.getProfessor(), ProfessorGetAllDto.class),
                course.getCourseStudents()
        );
    }

    public void deleteCourse(int id) throws EntityNotFoundException {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) {
            throw new EntityNotFoundException(String.format("Course with id %d does not exist!", id));
        }
        courseRepository.delete(existingCourse);
    }

    public Course updateCourse(int id, CourseRequest courseRequest) throws EntityNotFoundException {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) {
            throw new EntityNotFoundException(String.format("Course with id %d does not exist!", id));
        }
        Professor professor = professorRepository.findById(courseRequest.getProfessorId()).orElse(null);
        if (professor == null) {
            throw new EntityNotFoundException(
                    String.format("Professor with id %d does not exist!", courseRequest.getProfessorId()));
        }
        existingCourse.setNumberOfCredits(courseRequest.getNumberOfCredits());
        existingCourse.setTitle(courseRequest.getTitle());
        existingCourse.setDescription(courseRequest.getDescription());
        existingCourse.setStartDate(courseRequest.getStartDate());
        existingCourse.setEndDate(courseRequest.getEndDate());
        existingCourse.setProfessor(professor);
        return courseRepository.save(existingCourse);
    }

    public List<CourseByAvgProfSalaryDto> getCoursesByAvgProfSalaryDesc() throws EntityNotFoundException {
        List<CourseByAvgProfSalaryDto> courses = courseRepository.findCoursesByAvgProfSalaryDesc();
        if (courses.isEmpty()) {
            throw new EntityNotFoundException("No courses found!");
        }
        return courses;
    }

    public Course addCourseStudent(int courseId, StudentCourse studentCourse) throws EntityNotFoundException {
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse == null) {
            throw new EntityNotFoundException(String.format("Course with id %d does not exist!", courseId));
        }
        Student existingStudent = studentRepository.findById(studentCourse.getStudent().getId()).orElse(null);
        if (existingStudent == null) {
            throw new EntityNotFoundException(String.format("Student with id %d does not exist!", studentCourse.getStudent().getId()));
        }
        StudentCourse newStudentCourse = new StudentCourse();
        newStudentCourse.setCourse(existingCourse);
        newStudentCourse.setStudent(existingStudent);
        newStudentCourse.setGrade(studentCourse.getGrade());
        newStudentCourse.setFeedback(studentCourse.getFeedback());
        existingStudent.getStudentCourses().add(newStudentCourse);
        return courseRepository.save(existingCourse);
    }

    public void deleteCourseStudent(int courseId, int studentId) throws EntityNotFoundException {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent == null) {
            throw new EntityNotFoundException(String.format("Student with id %d does not exist!", studentId));
        }
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse == null) {
            throw new EntityNotFoundException(String.format("Course with id %d does not exist!", courseId));
        }
        StudentCourse existingStudentCourse = existingStudent.getStudentCourses().stream()
                .filter(studentCourse -> studentCourse.getCourse().getId() == courseId)
                .filter(studentCourse -> studentCourse.getStudent().getId() == studentId)
                .findFirst()
                .orElse(null);
        if (existingStudentCourse == null) {
            throw new EntityNotFoundException(String.format("Student with id %d is not enrolled in course with id %d!", studentId, courseId));
        }
        existingStudent.getStudentCourses().remove(existingStudentCourse);
        studentRepository.save(existingStudent);
    }
}
