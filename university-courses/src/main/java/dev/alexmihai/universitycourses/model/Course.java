package dev.alexmihai.universitycourses.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "Courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private int numberOfCredits;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne  // Bidirectional relationship - many courses can be taught by one professor (many-to-one)
    @JoinColumn(name = "professor_id")
    @JsonBackReference  // to avoid infinite recursion (see Professor.java)
    private Professor professor;

    @JsonManagedReference  // to avoid infinite recursion (see StudentCourse.java)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // avoid infinite recursion
    private List<StudentCourse> courseStudents = new ArrayList<>();  // many-to-many relationship
}
