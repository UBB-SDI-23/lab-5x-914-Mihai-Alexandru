package dev.alexmihai.universitycourses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfessorByNumStudsDto {
    private int id;
    private String profFirstName;
    private String profLastName;

    private long numStudents;
}
