import { TestBed } from '@angular/core/testing';

import { RecipeDetailsService } from './recipeDetails.service';

describe('Recipe2ServiceService', () => {
  let service: RecipeDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecipeDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
