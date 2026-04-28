import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainLayoutMenuComponent } from './main-layout-menu.component';

describe('MainLayoutMenuComponent', () => {
  let component: MainLayoutMenuComponent;
  let fixture: ComponentFixture<MainLayoutMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MainLayoutMenuComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(MainLayoutMenuComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
