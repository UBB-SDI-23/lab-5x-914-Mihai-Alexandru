import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from '../models/professor';
import { ProfessorByNumStudsDto } from '../dto/professor-by-num-studs-dto';

@Injectable({
  providedIn: 'root',
})
export class ProfessorService {
  private baseURL = 'http://localhost:8080/api/professors';

  constructor(private httpClient: HttpClient) {}

  getProfessorsList(): Observable<Professor[]> {
    return this.httpClient.get<Professor[]>(`${this.baseURL}`);
  }

  getProfessorsByNumStuds(): Observable<ProfessorByNumStudsDto[]> {
    return this.httpClient.get<ProfessorByNumStudsDto[]>(
      `${this.baseURL}/stats-profs-by-num-studs-desc`
    );
  }

  createProfessor(professor: Professor): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, professor);
  }

  getProfessorById(id: number): Observable<Professor> {
    return this.httpClient.get<Professor>(`${this.baseURL}/${id}`);
  }

  updateProfessor(id: number, professor: Professor): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, professor);
  }

  deleteProfessor(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
