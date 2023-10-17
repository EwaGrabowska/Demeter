import { Component } from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";

@Component({
  selector: 'app-liked-recipe',
  templateUrl: './liked-recipe.component.html',
  styleUrls: ['./liked-recipe.component.css']
})
export class LikedRecipeComponent {
  recipes!: RecipeResponse[];
}
