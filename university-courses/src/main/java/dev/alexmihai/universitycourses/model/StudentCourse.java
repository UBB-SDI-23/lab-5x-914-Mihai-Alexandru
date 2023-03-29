package dev.alexmihai.universitycourses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "StudentsCourses")
public class StudentCourse implements Serializable {
    @EmbeddedId
    private StudentCourseId id = new StudentCourseId();

    @ManyToOne
    @MapsId("studentId")
    @JsonBackReference(value = "student-in-StudentCourse")  // to avoid infinite recursion (see Student.java)
    private Student student;  // many-to-many relationship

    @ManyToOne
    @MapsId("courseId")
    @JsonBackReference(value = "course-in-StudentCourse")  // to avoid infinite recursion (see Course.java)
    private Course course;  // many-to-many relationship

    // additional fields for the many-to-many relationship:
    private int grade;
    private String feedback;
}
