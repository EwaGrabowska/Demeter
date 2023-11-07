import { TestBed } from '@angular/core/testing';

import { RecipePageService } from './recipe-page.service';

describe('RecipePageService', () => {
  let service: RecipePageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecipePageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
