import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LikedRecipesComponent } from './liked-recipes.component';

describe('LikedRecipeComponent', () => {
  let component: LikedRecipesComponent;
  let fixture: ComponentFixture<LikedRecipesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LikedRecipesComponent]
    });
    fixture = TestBed.createComponent(LikedRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
