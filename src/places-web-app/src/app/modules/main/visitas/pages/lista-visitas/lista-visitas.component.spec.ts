import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaVisitasComponent } from './lista-visitas.component';

describe('ListaVisitasComponent', () => {
  let component: ListaVisitasComponent;
  let fixture: ComponentFixture<ListaVisitasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListaVisitasComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ListaVisitasComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
