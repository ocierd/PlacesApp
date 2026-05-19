import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CredencialesRegistroStepComponent } from './credenciales-registro-step.component';

describe('CredencialesRegistroStepComponent', () => {
  let component: CredencialesRegistroStepComponent;
  let fixture: ComponentFixture<CredencialesRegistroStepComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CredencialesRegistroStepComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CredencialesRegistroStepComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
