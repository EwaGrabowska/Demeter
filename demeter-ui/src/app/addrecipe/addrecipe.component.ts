import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RecipeRequest} from "./recipeRequest";
import {Ingredient} from "./ingredient";
import {Router} from "@angular/router";

@Component({
  selector: 'app-addrecipe',
  templateUrl: './addrecipe.component.html',
  styleUrls: ['./addrecipe.component.css']
})
export class AddrecipeComponent {
  // recipeForm: FormGroup;
  recipeRequest= new RecipeRequest ('', '', 0,
    [new Ingredient(0, '', '')],
    '', 0, 0, 0, 0);


  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router) {
    // this.recipeForm = this.formBuilder.group({
    //   name: ['', Validators.required],
    //   author: ['', Validators.required],
    //   servingSize: [0, Validators.required],
    //   method: ['', Validators.required],
    //   ingredientList: this.formBuilder.array([], Validators.minLength(1)),
    //   price: [0],
    //   preparationTime: [0],
    //   cookingTime: [0],
    //   restingTime: [0]
    // });
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

  addIngredient() {
    this.recipeRequest.ingredientList.push({
      quantity: 0,
      measuringUnites: '',
      name: ''
    });
  }

  removeIngredient(index: number) {
    this.recipeRequest.ingredientList.splice(index, 1);
  }

  moveIngredientUp(index: number) {
    if (index > 0) {
      const currentIngredient = { ...this.recipeRequest.ingredientList[index] };
      const previousIngredient = { ...this.recipeRequest.ingredientList[index - 1] };
      this.recipeRequest.ingredientList[index] = previousIngredient;
      this.recipeRequest.ingredientList[index - 1] = currentIngredient;
    }
  }

  moveIngredientDown(index: number) {
    if (index < this.recipeRequest.ingredientList.length - 1) {
      const currentIngredient = { ...this.recipeRequest.ingredientList[index] };
      const nextIngredient = { ...this.recipeRequest.ingredientList[index + 1] };
      this.recipeRequest.ingredientList[index] = nextIngredient;
      this.recipeRequest.ingredientList[index + 1] = currentIngredient;
    }
  }

  resetForm() {
    this.recipeRequest = {
      name: '',
      author: '',
      servingSize: 0,
      ingredientList: [
        {
          quantity: 0,
          measuringUnites: '',
          name: ''
        }
      ],
      method: '',
      price: 0,
      preparationTime: 0,
      cookingTime: 0,
      restingTime: 0
    };
  }
  submitForm() {
    // if (this.recipeForm.invalid) {
    //   console.log('cos nie halo', this.recipe);
    //   return;
    // }
    console.log(this.recipeRequest)
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:8080'
      })
    };

    this.http.post<RecipeRequest>("http://localhost:8080/recipes", this.recipeRequest).subscribe({
      next: response => {
        console.log('Form submitted successfully!', response);
        this.resetForm();
      },
      error: error => {
        console.error('An error occurred while submitting the form:', error);
      }
    });
  }
}

