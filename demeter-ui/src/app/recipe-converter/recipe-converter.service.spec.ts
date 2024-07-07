import { TestBed } from '@angular/core/testing';

import { RecipeConverterService } from './recipe-converter.service';

describe('RecipeConverterService', () => {
  let service: RecipeConverterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecipeConverterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
