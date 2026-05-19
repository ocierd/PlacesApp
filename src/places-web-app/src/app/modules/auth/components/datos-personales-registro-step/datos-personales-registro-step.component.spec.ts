import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatosPersonalesRegistroStepComponent } from './datos-personales-registro-step.component';

describe('DatosPersonalesRegistroStepComponent', () => {
  let component: DatosPersonalesRegistroStepComponent;
  let fixture: ComponentFixture<DatosPersonalesRegistroStepComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DatosPersonalesRegistroStepComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DatosPersonalesRegistroStepComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
