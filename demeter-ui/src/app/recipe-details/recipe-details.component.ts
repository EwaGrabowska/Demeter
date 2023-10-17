import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RecipeResponse} from "../home/recipeResponse";
import {Recipe2ServiceService} from "./recipe2-service.service";
import {Location} from "@angular/common";
import {RecipeService} from "../home/recipeService";
import {UserService} from "./user.service";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit{

  recipe!: RecipeResponse;
  hasBeenLikedByTheUser?: boolean;
  hasBeenSubscibed?: boolean;

  constructor(private activateRoute: ActivatedRoute, private recipeService: RecipeService,
              private recipe2ServiceService: Recipe2ServiceService, private location: Location,
              private userSrvice: UserService) {
  }

  ngOnInit(): void {
    this.recipe2ServiceService.selectedRecipe$.subscribe((value) => {
      this.recipe = value;
    });

  }

  likeRecipe() {
    this.recipe2ServiceService.likeRecipe(this.recipe.id).subscribe(data => {
      this.recipe=data;
    });
    this.recipeService.triggerRefresh();
  }

  dislikeRecipe() {
    this.recipe2ServiceService.dislikeRecipe(this.recipe.id).subscribe(data => {
      this.recipe=data;
    });
    this.recipeService.triggerRefresh();
  }

  goBack() {
    this.location.back();
  }

  subscribeToUser() {
    // this.userSrvice.subscribeToUser();
  }

  unsubscribeToUser() {
    // this.userSrvice.unsubscribeToUser();
  }
}
