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
public class ProfessorRequest {
    // no id because it is auto-generated

    @NotBlank(message = "First name is required!")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    private String lastName;

    private String email;
    private String phone;

    @Min(value = 0, message = "Salary must be a positive integer!")
    @NotNull(message = "Salary is required!")
    private int salary;

    // TODO: allow insertion of new/existing courses when creating a new professor
    // private List<Course> courses = new ArrayList<>();
}
