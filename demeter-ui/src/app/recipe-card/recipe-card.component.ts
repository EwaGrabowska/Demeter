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
  parentComponent!: string;

  @Input()
  recipe!: RecipeResponse;
  event!: string;
  constructor(private router: Router, private recipeService: RecipeDetailsService) {
  }

  handleCardClick() {
    this.recipeService.setRecipe(this.recipe);
    if(this.parentComponent === "notes"){
      this.router.navigate(['/edition'])
    }else{
      this.router.navigate(['/details'])
    }

  }
}
