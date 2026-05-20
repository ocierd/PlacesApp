import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModulosDialogComponent } from './modulos-dialog.component';

describe('ModulosDialogComponent', () => {
  let component: ModulosDialogComponent;
  let fixture: ComponentFixture<ModulosDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ModulosDialogComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ModulosDialogComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
