import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogsExampleComponent } from './dialogs-example.component';

describe('DialogsExampleComponent', () => {
  let component: DialogsExampleComponent;
  let fixture: ComponentFixture<DialogsExampleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DialogsExampleComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DialogsExampleComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
