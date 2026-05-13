import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroComponentComponent } from './registro.component';

describe('RegistroComponentComponent', () => {
  let component: RegistroComponentComponent;
  let fixture: ComponentFixture<RegistroComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegistroComponentComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(RegistroComponentComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
