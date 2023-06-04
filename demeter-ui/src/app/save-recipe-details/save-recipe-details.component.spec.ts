import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveRecipeDetailsComponent } from './save-recipe-details.component';

describe('SaveRecipeDetailsComponent', () => {
  let component: SaveRecipeDetailsComponent;
  let fixture: ComponentFixture<SaveRecipeDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SaveRecipeDetailsComponent]
    });
    fixture = TestBed.createComponent(SaveRecipeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
