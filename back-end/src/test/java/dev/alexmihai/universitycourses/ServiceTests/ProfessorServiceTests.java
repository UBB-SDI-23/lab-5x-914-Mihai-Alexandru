package dev.alexmihai.universitycourses.ServiceTests;

import dev.alexmihai.universitycourses.dto.ProfessorByNumStudsDto;
import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.exception.EntityNotFoundException;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.repository.ProfessorRepository;
import dev.alexmihai.universitycourses.service.ProfessorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfessorServiceTests {

    @Autowired
    private ProfessorService professorService;

    @MockBean
    private ProfessorRepository professorRepository;

    @Test
    public void findBySalaryGreaterThanTest() throws EntityNotFoundException {

        // test if the method throws an exception when the list is empty
        assertThrows(EntityNotFoundException.class, () -> {
            professorService.findBySalaryGreaterThan(1000);
        });

        // test if the method returns the correct list - one element
        when(professorRepository.findBySalaryGreaterThan(1000)).thenReturn(Stream.of(
                        new Professor(0, "a", "b", "c", "d", 1001, null))
                .toList());
        assertEquals(professorService.findBySalaryGreaterThan(1000), Stream.of(
                        new ProfessorGetAllDto(0, "a", "b", "c", "d", 1001))
                .toList());

        // test if the method returns the correct list - multiple elements
        when(professorRepository.findBySalaryGreaterThan(1000)).thenReturn(Stream.of(
                        new Professor(0, "a", "b", "c", "d", 1001, null),
                        new Professor(1, "a", "b", "c", "d", 1002, null),
                        new Professor(2, "a", "b", "c", "d", 1003, null))
                .toList());
        assertEquals(professorService.findBySalaryGreaterThan(1000), Stream.of(
                        new ProfessorGetAllDto(0, "a", "b", "c", "d", 1001),
                        new ProfessorGetAllDto(1, "a", "b", "c", "d", 1002),
                        new ProfessorGetAllDto(2, "a", "b", "c", "d", 1003))
                .toList());

        // check if the method returns the correct list - one element, but the wrong one
        when(professorRepository.findBySalaryGreaterThan(1000)).thenReturn(Stream.of(
                        new Professor(0, "a", "b", "c", "d", 1001, null))
                .toList());
        assertNotEquals(professorService.findBySalaryGreaterThan(1000), Stream.of(
                        new ProfessorGetAllDto(1, "a", "b", "c", "d", 1001))
                .toList());
    }

    @Test
    public void findProfsByNumStudsDescTest() throws EntityNotFoundException {

        // test if the method throws an exception when the list is empty
        assertThrows(EntityNotFoundException.class, () -> {
            professorService.getProfsByNumStudsDesc();
        });

        // test if the method returns the correct list - one element
        when(professorRepository.findProfsByNumStudsDesc()).thenReturn(Stream.of(
                        new ProfessorByNumStudsDto(0, "a", "b", 1))
                .toList());
        assertEquals(professorService.getProfsByNumStudsDesc(), Stream.of(
                        new ProfessorByNumStudsDto(0, "a", "b", 1))
                .toList());

        // test if the method returns the correct list - multiple elements
        when(professorRepository.findProfsByNumStudsDesc()).thenReturn(Stream.of(
                        new ProfessorByNumStudsDto(0, "a", "b", 1),
                        new ProfessorByNumStudsDto(1, "a", "b", 2),
                        new ProfessorByNumStudsDto(2, "a", "b", 3))
                .toList());
        assertEquals(professorService.getProfsByNumStudsDesc(), Stream.of(
                        new ProfessorByNumStudsDto(0, "a", "b", 1),
                        new ProfessorByNumStudsDto(1, "a", "b", 2),
                        new ProfessorByNumStudsDto(2, "a", "b", 3))
                .toList());

        // check if the method returns the correct list - one element, but the wrong one
        when(professorRepository.findProfsByNumStudsDesc()).thenReturn(Stream.of(
                        new ProfessorByNumStudsDto(0, "a", "b", 1))
                .toList());
        assertNotEquals(professorService.getProfsByNumStudsDesc(), Stream.of(
                        new ProfessorByNumStudsDto(1, "a", "b", 1))
                .toList());
    }
}
