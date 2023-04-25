import { Component } from '@angular/core';
import { Professor } from '../../models/professor';
import { ProfessorService } from '../../services/professor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professor-create',
  templateUrl: './professor-create.component.html',
  styleUrls: ['./professor-create.component.css'],
})
export class ProfessorCreateComponent {
  professor: Professor = new Professor();

  constructor(
    private professorService: ProfessorService,
    private router: Router
  ) {}

  saveProfessor() {
    // TODO: fix deprecated method call
    this.professorService.createProfessor(this.professor).subscribe(
      (data) => {
        console.log(data);
        this.goToProfessorList();
      },
      (error) => console.log(error)
    );
  }

  goToProfessorList() {
    this.router.navigate(['/professors']);
  }

  onSubmit() {
    console.log(this.professor);
    this.saveProfessor();
  }

  onCancel() {
    this.goToProfessorList();
  }
}
