import {Component, Input} from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";
import {Router} from "@angular/router";
import {RecipeDetailsService} from "../recipe-details/recipeDetails.service";

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.css']
})
export class RecipeCardComponent {

  @Input()
  recipe!: RecipeResponse;
  constructor(private router: Router, private recipeService: RecipeDetailsService) {
  }

  handleCardClick() {
    this.recipeService.setRecipe(this.recipe);
    this.router.navigate(['/details'])
  }
}
