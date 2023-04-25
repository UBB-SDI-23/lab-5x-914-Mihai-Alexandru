package dev.alexmihai.universitycourses.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseAssignProfessorPostDto {
    @NotNull(message = "Number of credits is required!")
    private int id;

    @Min(value = 0, message = "Number of credits must be a positive integer!")
    @NotNull(message = "Number of credits is required!")
    private int numberOfCredits;

    @NotBlank(message = "Title is required!")
    private String title;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
