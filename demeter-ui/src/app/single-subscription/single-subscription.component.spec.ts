import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleSubscriptionComponent } from './single-subscription.component';

describe('SingleSubscriptionComponent', () => {
  let component: SingleSubscriptionComponent;
  let fixture: ComponentFixture<SingleSubscriptionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SingleSubscriptionComponent]
    });
    fixture = TestBed.createComponent(SingleSubscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
