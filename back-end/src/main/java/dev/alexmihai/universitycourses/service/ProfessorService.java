package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.CourseAssignProfessorPostDto;
import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.dto.ProfessorByNumStudsDto;
import dev.alexmihai.universitycourses.dto.ProfessorRequest;
import dev.alexmihai.universitycourses.exception.EntityNotFoundException;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.repository.CourseRepository;
import dev.alexmihai.universitycourses.repository.ProfessorRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    CourseRepository courseRepository;

    public Professor saveProfessor(ProfessorRequest professorRequest) {
        Professor professor = new Professor();
        ObjectMapperUtils.map(professorRequest, professor);  // map from professorRequest to professor (using ModelMapper)
        return professorRepository.save(professor);
    }

    public List<ProfessorGetAllDto> getProfessors() throws EntityNotFoundException {
        List<Professor> professors = professorRepository.findAll();
        if (professors.isEmpty()) {
            throw new EntityNotFoundException("No professors found!");
        }
        return ObjectMapperUtils.mapAll(professors, ProfessorGetAllDto.class);
    }

    public Professor getProfessorById(int id) throws EntityNotFoundException {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor == null) {
            throw new EntityNotFoundException(String.format("Professor with id %d does not exist!", id));
        }
        return professor;
    }

    public List<ProfessorGetAllDto> findBySalaryGreaterThan(int salary) throws EntityNotFoundException {
        List<Professor> professors = professorRepository.findBySalaryGreaterThan(salary);
        if (professors.isEmpty()) {
            throw new EntityNotFoundException(String.format("No professors with salary greater than %d found!", salary));
        }
        return ObjectMapperUtils.mapAll(professors, ProfessorGetAllDto.class);
    }

    public void deleteProfessor(int id) throws EntityNotFoundException {
        Professor existingProfessor = professorRepository.findById(id).orElse(null);
        if (existingProfessor == null) {
            throw new EntityNotFoundException(String.format("Professor with id %d does not exist!", id));
        }
        professorRepository.delete(existingProfessor);
    }

    public Professor updateProfessor(int id, ProfessorRequest professorRequest) throws EntityNotFoundException {
        Professor existingProfessor = professorRepository.findById(id).orElse(null);
        if (existingProfessor == null) {
            throw new EntityNotFoundException(
                    String.format("Professor with id %d does not exist!", id));
        }
        existingProfessor.setFirstName(professorRequest.getFirstName());
        existingProfessor.setLastName(professorRequest.getLastName());
        existingProfessor.setEmail(professorRequest.getEmail());
        existingProfessor.setPhone(professorRequest.getPhone());
        existingProfessor.setSalary(professorRequest.getSalary());
        return professorRepository.save(existingProfessor);
    }

    public List<ProfessorByNumStudsDto> getProfsByNumStudsDesc() throws EntityNotFoundException {
        List<ProfessorByNumStudsDto> professors = professorRepository.findProfsByNumStudsDesc();
        if (professors.isEmpty()) {
            throw new EntityNotFoundException("No professors found!");
        }
        return professors;
    }

    // lab activity
    public Professor addCourseListToProfessor(int professorId, List<CourseAssignProfessorPostDto> courses) throws EntityNotFoundException {
        Professor professor = professorRepository.findById(professorId).orElse(null);
        if (professor == null) {
            throw new EntityNotFoundException(String.format("Professor with id %d does not exist!", professorId));
        }

        for (CourseAssignProfessorPostDto course : courses) {
            if (course == null) {
                throw new EntityNotFoundException("Course does not exist!");
            }
            Course existingCourse = courseRepository.findById(course.getId()).orElse(null);
            if (existingCourse == null) {
                throw new EntityNotFoundException(String.format("Course with id %d does not exist!", course.getId()));
            }
        }

        List<Course> mappedCourses = ObjectMapperUtils.mapAll(courses, Course.class);
        for (Course course : mappedCourses) {
            course.setProfessor(professor);
            courseRepository.save(course);
        }

        return professorRepository.save(professor);
    }
}
