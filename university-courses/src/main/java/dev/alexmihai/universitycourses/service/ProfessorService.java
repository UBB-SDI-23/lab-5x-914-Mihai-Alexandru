package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.dto.ProfessorByNumStudsDto;
import dev.alexmihai.universitycourses.dto.ProfessorRequest;
import dev.alexmihai.universitycourses.exception.EntityNotFoundException;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.repository.ProfessorRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;

    public Professor saveProfessor(ProfessorRequest professorRequest) {
        Professor professor = new Professor();
        ObjectMapperUtils.map(professorRequest, professor);  // map from professorRequest to professor (using ModelMapper)
        return repository.save(professor);
    }

    public List<Professor> saveProfessors(List<Professor> professors) {
        return repository.saveAll(professors);
    }

    public List<ProfessorGetAllDto> getProfessors() throws EntityNotFoundException {
        List<Professor> professors = repository.findAll();
        if (professors.isEmpty()) {
            throw new EntityNotFoundException("No professors found!");
        }
        return ObjectMapperUtils.mapAll(professors, ProfessorGetAllDto.class);
    }

    public Professor getProfessorById(int id) throws EntityNotFoundException {
        Professor professor = repository.findById(id).orElse(null);
        if (professor == null) {
            throw new EntityNotFoundException(String.format("Professor with id %d does not exist!", id));
        }
        return professor;
    }

    public List<ProfessorGetAllDto> findBySalaryGreaterThan(int salary) throws EntityNotFoundException {
        List<Professor> professors = repository.findBySalaryGreaterThan(salary);
        if (professors.isEmpty()) {
            throw new EntityNotFoundException(String.format("No professors with salary greater than %d found!", salary));
        }
        return ObjectMapperUtils.mapAll(professors, ProfessorGetAllDto.class);
    }

    public void deleteProfessor(int id) throws EntityNotFoundException {
        Professor existingProfessor = repository.findById(id).orElse(null);
        if (existingProfessor == null) {
            throw new EntityNotFoundException(String.format("Professor with id %d does not exist!", id));
        }
        repository.delete(existingProfessor);
    }

    public Professor updateProfessor(int id, ProfessorRequest professorRequest) throws EntityNotFoundException {
        Professor existingProfessor = repository.findById(id).orElse(null);
        if (existingProfessor == null) {
            throw new EntityNotFoundException(
                    String.format("Professor with id %d does not exist!", id));
        }
        existingProfessor.setFirstName(professorRequest.getFirstName());
        existingProfessor.setLastName(professorRequest.getLastName());
        existingProfessor.setEmail(professorRequest.getEmail());
        existingProfessor.setPhone(professorRequest.getPhone());
        existingProfessor.setSalary(professorRequest.getSalary());
        return repository.save(existingProfessor);
    }

    public List<ProfessorByNumStudsDto> getProfsByNumStudsDesc() throws EntityNotFoundException {
        List<ProfessorByNumStudsDto> professors = repository.findProfsByNumStudsDesc();
        if (professors.isEmpty()) {
            throw new EntityNotFoundException("No professors found!");
        }
        return professors;
    }
}
