import { TestBed } from '@angular/core/testing';

import { LikedRecipesPageService } from './liked-recipes-page.service';

describe('LikedrecipePageService', () => {
  let service: LikedRecipesPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LikedRecipesPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
