import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorCreateComponent } from './components/professor-create/professor-create.component';
import { ProfessorListComponent } from './components/professor-list/professor-list.component';
import { ProfessorUpdateComponent } from './components/professor-update/professor-update.component';
import { ProfessorDetailsComponent } from './components/professor-details/professor-details.component';
import { ProfessorFilterByNumStudsComponent } from './components/professor-filter-by-num-studs/professor-filter-by-num-studs.component';

const routes: Routes = [
  { path: 'professors', component: ProfessorListComponent },
  { path: 'professor-create', component: ProfessorCreateComponent },
  { path: 'professor-update/:id', component: ProfessorUpdateComponent },
  { path: 'professor-details/:id', component: ProfessorDetailsComponent },
  {
    path: 'professor-filter-by-num-studs',
    component: ProfessorFilterByNumStudsComponent,
  },

  // TODO: possibly add home page component and route:
  { path: '', redirectTo: 'professors', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
