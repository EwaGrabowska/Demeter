import { TestBed } from '@angular/core/testing';

import { SubscriptionsPageService } from './subscriptions-page.service';

describe('SubscriptionsPageService', () => {
  let service: SubscriptionsPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubscriptionsPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
