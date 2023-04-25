import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Professor } from 'src/app/models/professor';
import { ProfessorService } from 'src/app/services/professor.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-professor-update',
  templateUrl: './professor-update.component.html',
  styleUrls: ['./professor-update.component.css'],
})
export class ProfessorUpdateComponent implements OnInit {
  id!: number;
  professor: Professor = new Professor();

  constructor(
    private professorService: ProfessorService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    // TODO: fix deprecated method
    this.professorService.getProfessorById(this.id).subscribe(
      (data) => {
        this.professor = data;
      },
      (error) => console.log(error)
    );
  }

  goToProfessorList() {
    this.router.navigate(['/professors']);
  }

  onSubmit() {
    this.professorService.updateProfessor(this.id, this.professor).subscribe(
      (data) => {
        this.goToProfessorList();
      },
      (error) => console.log(error)
    );
  }

  goBack(): void {
    this.location.back();
  }
}
