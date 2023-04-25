package dev.alexmihai.universitycourses.repository;

import dev.alexmihai.universitycourses.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
