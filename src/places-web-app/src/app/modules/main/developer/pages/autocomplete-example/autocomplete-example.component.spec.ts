import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutocompleteExampleComponent } from './autocomplete-example.component';

describe('AutocompleteExampleComponent', () => {
  let component: AutocompleteExampleComponent;
  let fixture: ComponentFixture<AutocompleteExampleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AutocompleteExampleComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AutocompleteExampleComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
