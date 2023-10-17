import {Component, Input} from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";
import {Router} from "@angular/router";
import {Recipe2ServiceService} from "../recipe-details/recipe2-service.service";

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.css']
})
export class RecipeCardComponent {

  @Input()
  recipe!: RecipeResponse;
  constructor(private router: Router, private recipeService: Recipe2ServiceService) {
  }

  handleCardClick() {
    this.recipeService.setRecipe(this.recipe);
    this.router.navigate(['/details'])
  }
}
