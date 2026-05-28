import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableExampleComponent } from './table-example.component';

describe('TableExampleComponent', () => {
  let component: TableExampleComponent;
  let fixture: ComponentFixture<TableExampleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TableExampleComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TableExampleComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
