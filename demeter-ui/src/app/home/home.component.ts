import {Component, OnDestroy, OnInit} from '@angular/core';
import {RecipeResponse} from "./recipeResponse";
import {RecipeService} from "./recipeService";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnDestroy{
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
