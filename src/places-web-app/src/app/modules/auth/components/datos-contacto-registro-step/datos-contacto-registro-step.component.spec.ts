import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatosContactoRegistroStepComponent } from './datos-contacto-registro-step.component';

describe('DatosContactoRegistroStepComponent', () => {
  let component: DatosContactoRegistroStepComponent;
  let fixture: ComponentFixture<DatosContactoRegistroStepComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DatosContactoRegistroStepComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DatosContactoRegistroStepComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
