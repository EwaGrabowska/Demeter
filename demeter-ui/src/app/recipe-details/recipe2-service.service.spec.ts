import { TestBed } from '@angular/core/testing';

import { Recipe2ServiceService } from './recipe2-service.service';

describe('Recipe2ServiceService', () => {
  let service: Recipe2ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Recipe2ServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
