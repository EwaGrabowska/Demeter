import {Component, OnDestroy} from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";
import {Subscription} from "rxjs";
import {RecipeService} from "../home/recipeService";

@Component({
  selector: 'app-subscribtions',
  templateUrl: './subscribtions.component.html',
  styleUrls: ['./subscribtions.component.css']
})
export class SubscribtionsComponent implements OnDestroy{
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
