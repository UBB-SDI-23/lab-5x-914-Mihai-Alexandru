import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Professor } from 'src/app/models/professor';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css'],
})
export class ProfessorListComponent {
  professors!: Professor[];

  constructor(
    private professorService: ProfessorService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getProfessors();
  }

  private getProfessors() {
    this.professorService.getProfessorsList().subscribe((data) => {
      this.professors = data;
    });
  }

  sortProfessorsByFirstLastName() {
    this.professors.sort((a, b) => {
      if (a.firstName === b.firstName) {
        return a.lastName.localeCompare(b.lastName);
      }
      return a.firstName.localeCompare(b.firstName);
    });
  }

  createProfessor() {
    this.router.navigate(['professor-create']);
  }

  updateProfessor(id: number) {
    this.router.navigate(['professor-update', id]);
  }

  deleteProfessor(id: number) {
    this.professorService.deleteProfessor(id).subscribe(
      (data) => {
        console.log(data);
        this.getProfessors();
      },
      (error) => console.log(error)
    );
  }

  professorDetails(id: number) {
    this.router.navigate(['professor-details', id]);
  }
}
