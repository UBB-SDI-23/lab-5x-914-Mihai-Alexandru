package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.dto.CourseAssignProfessorPostDto;
import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.dto.ProfessorByNumStudsDto;
import dev.alexmihai.universitycourses.dto.ProfessorRequest;
import dev.alexmihai.universitycourses.exception.EntityNotFoundException;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")  // allow requests from any origin (for development purposes) - this is not recommended for production
@RestController
@RequestMapping("/api")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/professors")
    public ResponseEntity<Professor> addProfessor(@RequestBody @Valid ProfessorRequest professorRequest) {
        return new ResponseEntity<>(service.saveProfessor(professorRequest), HttpStatus.CREATED);
    }

    @GetMapping("/professors")
    public ResponseEntity<List<ProfessorGetAllDto>> findAllProfessors() throws EntityNotFoundException {
        return ResponseEntity.ok(service.getProfessors());
    }

    @GetMapping("/professors/{id}")
    public ResponseEntity<Professor> findProfessorById(@PathVariable int id) throws EntityNotFoundException {
        return ResponseEntity.ok(service.getProfessorById(id));
    }

    @RequestMapping(value = "/professors/salary-greater/{salary}", method = RequestMethod.GET)
    public ResponseEntity<List<ProfessorGetAllDto>> findBySalaryGreaterThan(@PathVariable int salary) throws EntityNotFoundException {
        return ResponseEntity.ok(service.findBySalaryGreaterThan(salary));
    }

    @PutMapping("/professors/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable int id, @RequestBody @Valid ProfessorRequest professorRequest) throws EntityNotFoundException {
        return ResponseEntity.ok(service.updateProfessor(id, professorRequest));
    }

    @DeleteMapping("/professors/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable int id) throws EntityNotFoundException {
        service.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/professors/stats-profs-by-num-studs-desc")
    public ResponseEntity<List<ProfessorByNumStudsDto>> getProfsByNumStudsDesc() throws EntityNotFoundException {
        return ResponseEntity.ok(service.getProfsByNumStudsDesc());
    }

    /*
    Assign a list of existing courses to an existing professor.
    If the courses are already assigned to another professor, they will be unassigned from that professor and
    assigned to the given professor.
     */
    @PostMapping("/professors/{professorId}/courses")
    public ResponseEntity<Professor> addCourseListToProfessor(@PathVariable int professorId, @RequestBody List<CourseAssignProfessorPostDto> courses) throws EntityNotFoundException {
        return new ResponseEntity<>(service.addCourseListToProfessor(professorId, courses), HttpStatus.CREATED);
    }
}
