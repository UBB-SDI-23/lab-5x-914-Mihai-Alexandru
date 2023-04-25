import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfessorListComponent } from './components/professor-list/professor-list.component';
import { ProfessorCreateComponent } from './components/professor-create/professor-create.component';
import { FormsModule } from '@angular/forms';
import { ProfessorUpdateComponent } from './components/professor-update/professor-update.component';
import { ProfessorDetailsComponent } from './components/professor-details/professor-details.component';
import { ProfessorFilterByNumStudsComponent } from './components/professor-filter-by-num-studs/professor-filter-by-num-studs.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfessorListComponent,
    ProfessorCreateComponent,
    ProfessorUpdateComponent,
    ProfessorDetailsComponent,
    ProfessorFilterByNumStudsComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
