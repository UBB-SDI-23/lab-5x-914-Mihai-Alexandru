import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Professor } from 'src/app/models/professor';
import { ProfessorService } from 'src/app/services/professor.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-professor-details',
  templateUrl: './professor-details.component.html',
  styleUrls: ['./professor-details.component.css'],
})
export class ProfessorDetailsComponent {
  id!: number;
  professor!: Professor;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private professorService: ProfessorService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.professor = new Professor();
    this.professorService.getProfessorById(this.id).subscribe((data) => {
      this.professor = data;
    });
  }

  goToProfessorList(): void {
    this.router.navigate(['/professors']);
  }

  goBack(): void {
    this.location.back();
  }
}
