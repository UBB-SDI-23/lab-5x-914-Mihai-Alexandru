import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorFilterByNumStudsComponent } from './professor-filter-by-num-studs.component';

describe('ProfessorFilterByNumStudsComponent', () => {
  let component: ProfessorFilterByNumStudsComponent;
  let fixture: ComponentFixture<ProfessorFilterByNumStudsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorFilterByNumStudsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessorFilterByNumStudsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
