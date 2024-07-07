import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeConverterComponent } from './recipe-converter.component';

describe('RecipeConverterComponent', () => {
  let component: RecipeConverterComponent;
  let fixture: ComponentFixture<RecipeConverterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RecipeConverterComponent]
    });
    fixture = TestBed.createComponent(RecipeConverterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
