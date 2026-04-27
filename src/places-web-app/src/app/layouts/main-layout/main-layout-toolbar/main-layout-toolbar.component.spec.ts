import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainLayoutToolbarComponent } from './main-layout-toolbar.component';

describe('MainLayoutToolbarComponent', () => {
  let component: MainLayoutToolbarComponent;
  let fixture: ComponentFixture<MainLayoutToolbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MainLayoutToolbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainLayoutToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
