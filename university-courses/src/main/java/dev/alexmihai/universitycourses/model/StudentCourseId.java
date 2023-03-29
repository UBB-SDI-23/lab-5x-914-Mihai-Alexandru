package dev.alexmihai.universitycourses.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Embeddable
public class StudentCourseId implements Serializable {
    // composite primary key:
    private int studentId;
    private int courseId;
}
