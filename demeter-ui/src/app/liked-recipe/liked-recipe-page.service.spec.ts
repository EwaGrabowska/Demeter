import { TestBed } from '@angular/core/testing';

import { LikedRecipePageService } from './liked-recipe-page.service';

describe('LikedrecipePageService', () => {
  let service: LikedRecipePageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LikedRecipePageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
