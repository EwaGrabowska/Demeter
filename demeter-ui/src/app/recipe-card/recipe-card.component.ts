import {Component, Input, OnInit} from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";
import {Router} from "@angular/router";
import {RecipeDetailsService} from "../recipe-details/recipeDetails.service";
import {UserService} from "../recipe-details/user.service";

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.css']
})
export class RecipeCardComponent implements OnInit{
  @Input()
  parentComponent!: string;

  @Input()
  recipe!: RecipeResponse;
  photoUrl!: string | undefined;
  event!: string;
  constructor(private router: Router, private recipeService: RecipeDetailsService, private userService: UserService) {
  }
  ngOnInit(): void {
    if (this.recipe) {
      this.userService.getUserPhotoURLbySub(this.recipe.authorSub).subscribe(value => {
        this.photoUrl = value?.picture.valueOf();
      });
    }
  }

  handleCardClick() {
    this.recipeService.setRecipe(this.recipe);
    this.recipeService.setParentConponent(this.parentComponent);
    if(this.parentComponent === "notes"){
      this.router.navigate(['/edition'])
    }else{
      this.router.navigate(['/details'])
    }
  }
}
