package dev.alexmihai.universitycourses.repository;

import dev.alexmihai.universitycourses.dto.ProfessorByNumStudsDto;
import dev.alexmihai.universitycourses.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    /**
     * Get all the professors with a greater salary than the given integer.
     * The method is automatically implemented by Spring.
     * The arguments are the name of the field in the model class and the comparison operator.
     * The return value is a list of professors.
     */
    List<Professor> findBySalaryGreaterThan(int salary);

    @Query("SELECT new dev.alexmihai.universitycourses.dto.ProfessorByNumStudsDto(" +
            "p.id, p.firstName, p.lastName, COUNT(s.id)) " +
            "FROM Course c JOIN c.professor p " +
            "JOIN c.courseStudents s " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(s) DESC")
    List<ProfessorByNumStudsDto> findProfsByNumStudsDesc();
}
