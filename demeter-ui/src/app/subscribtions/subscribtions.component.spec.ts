import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribtionsComponent } from './subscribtions.component';

describe('SubscribtionsComponent', () => {
  let component: SubscribtionsComponent;
  let fixture: ComponentFixture<SubscribtionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SubscribtionsComponent]
    });
    fixture = TestBed.createComponent(SubscribtionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
