import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProfessorByNumStudsDto } from 'src/app/dto/professor-by-num-studs-dto';
import { ProfessorService } from 'src/app/services/professor.service';

@Component({
  selector: 'app-professor-filter-by-num-studs',
  templateUrl: './professor-filter-by-num-studs.component.html',
  styleUrls: ['./professor-filter-by-num-studs.component.css'],
})
export class ProfessorFilterByNumStudsComponent {
  professors!: ProfessorByNumStudsDto[];

  constructor(
    private professorService: ProfessorService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getProfessorsByNumStuds();
  }

  private getProfessorsByNumStuds() {
    this.professorService.getProfessorsByNumStuds().subscribe((data) => {
      this.professors = data;
    });
  }

  professorDetails(id: number) {
    this.router.navigate(['professor-details', id]);
  }
}
