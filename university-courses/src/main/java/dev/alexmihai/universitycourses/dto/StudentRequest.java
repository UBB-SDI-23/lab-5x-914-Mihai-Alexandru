package dev.alexmihai.universitycourses.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentRequest {
    // no id because it is auto-generated

    @NotBlank(message = "First name is required!")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    private String lastName;

    private String email;
    private String phone;

    @Min(value = 1, message = "Year of study must be a strictly positive integer!")
    @NotNull(message = "Year of study is required!")
    private int yearOfStudy;

    // The line below is commented because StudentCourse entities should be added later, after the student is created:
    // private List<StudentCourse> studentCourses = new ArrayList<>();
}
