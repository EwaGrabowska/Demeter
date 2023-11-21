import { TestBed } from '@angular/core/testing';

import { MyRecipesPageService } from './my-recipes-page.service';

describe('MyrecipeService', () => {
  let service: MyRecipesPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyRecipesPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
