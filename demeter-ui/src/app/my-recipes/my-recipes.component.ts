import {Component, OnDestroy} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {RecipeResponse} from "../home/recipeResponse";
import {Subscription} from "rxjs";
import {RecipeService} from "../home/recipeService";

@Component({
  selector: 'app-my-recipes',
  templateUrl: './my-recipes.component.html',
  styleUrls: ['./my-recipes.component.css']
})
export class MyRecipesComponent implements OnDestroy{
  recipes: Array<RecipeResponse> = [];
  getAllRecipeSubscription: Subscription;

  constructor(private recipeService: RecipeService) {
    this.getAllRecipeSubscription = recipeService.getAllRecipes().subscribe(data => {
      this.recipes = data;
    });
  }

  ngOnDestroy(): void {
    this.getAllRecipeSubscription.unsubscribe();
  }

}
