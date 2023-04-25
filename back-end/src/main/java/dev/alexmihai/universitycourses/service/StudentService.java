package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.StudentGetAllDto;
import dev.alexmihai.universitycourses.dto.StudentGetByIdDto;
import dev.alexmihai.universitycourses.dto.StudentRequest;
import dev.alexmihai.universitycourses.exception.EntityNotFoundException;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.Student;
import dev.alexmihai.universitycourses.model.StudentCourse;
import dev.alexmihai.universitycourses.repository.CourseRepository;
import dev.alexmihai.universitycourses.repository.StudentRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Student saveStudent(StudentRequest studentRequest) {
        Student newStudent = new Student();
        newStudent.setFirstName(studentRequest.getFirstName());
        newStudent.setLastName(studentRequest.getLastName());
        newStudent.setEmail(studentRequest.getEmail());
        newStudent.setPhone(studentRequest.getPhone());
        newStudent.setYearOfStudy(studentRequest.getYearOfStudy());
        return studentRepository.save(newStudent);
    }

    public List<StudentGetAllDto> getStudents() throws EntityNotFoundException {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new EntityNotFoundException("No students found!");
        }
        return ObjectMapperUtils.mapAll(students, StudentGetAllDto.class);
    }

    public StudentGetByIdDto getStudentById(int id) throws EntityNotFoundException {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new EntityNotFoundException(String.format("Student with id %d does not exist!", id));
        }
        return new StudentGetByIdDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                student.getYearOfStudy(),
                student.getStudentCourses()
        );
    }

    public void deleteStudent(int id) throws EntityNotFoundException {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            throw new EntityNotFoundException(String.format("Student with id %d does not exist!", id));
        }
        studentRepository.delete(existingStudent);
    }

    public Student updateStudent(int id, StudentRequest studentRequest) throws EntityNotFoundException {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            throw new EntityNotFoundException(String.format("Student with id %d does not exist!", id));
        }
        existingStudent.setFirstName(studentRequest.getFirstName());
        existingStudent.setLastName(studentRequest.getLastName());
        existingStudent.setEmail(studentRequest.getEmail());
        existingStudent.setPhone(studentRequest.getPhone());
        existingStudent.setYearOfStudy(studentRequest.getYearOfStudy());
        return studentRepository.save(existingStudent);
    }

    // attempt below:
    public Student addStudentCourse(int studentId, StudentCourse studentCourse) throws EntityNotFoundException {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent == null) {
            throw new EntityNotFoundException(String.format("Student with id %d does not exist!", studentId));
        }
        Course existingCourse = courseRepository.findById(studentCourse.getCourse().getId()).orElse(null);
        if (existingCourse == null) {
            throw new EntityNotFoundException(String.format("Course with id %d does not exist!", studentCourse.getCourse().getId()));
        }
        StudentCourse newStudentCourse = new StudentCourse();
        newStudentCourse.setCourse(existingCourse);
        newStudentCourse.setStudent(existingStudent);
        newStudentCourse.setGrade(studentCourse.getGrade());
        newStudentCourse.setFeedback(studentCourse.getFeedback());
        existingStudent.getStudentCourses().add(newStudentCourse);
        return studentRepository.save(existingStudent);
    }

    public void deleteStudentCourse(int studentId, int courseId) throws EntityNotFoundException {
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
