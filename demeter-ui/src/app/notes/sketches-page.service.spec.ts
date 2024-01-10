import { TestBed } from '@angular/core/testing';

import { SketchesPageService } from './sketches-page.service';

describe('SketchesPageService', () => {
  let service: SketchesPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SketchesPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
