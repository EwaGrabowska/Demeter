import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditionComponent } from './edition.component';

describe('EditionComponent', () => {
  let component: EditionComponent;
  let fixture: ComponentFixture<EditionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditionComponent]
    });
    fixture = TestBed.createComponent(EditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
