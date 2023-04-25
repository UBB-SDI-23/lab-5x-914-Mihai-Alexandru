import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorUpdateComponent } from './professor-update.component';

describe('ProfessorUpdateComponent', () => {
  let component: ProfessorUpdateComponent;
  let fixture: ComponentFixture<ProfessorUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessorUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
