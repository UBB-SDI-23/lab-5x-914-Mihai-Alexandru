package dev.alexmihai.universitycourses.dto;

import dev.alexmihai.universitycourses.model.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentGetByIdDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int yearOfStudy;
    private List<StudentCourse> studentCourses = new ArrayList<>();
}
