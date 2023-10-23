import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {RecipeResponse} from "../home/recipeResponse";
import {RecipeDetailsService} from "./recipeDetails.service";
import {Location} from "@angular/common";
import {RecipeService} from "../home/recipeService";
import {UserService} from "./user.service";
import {UserResponse} from "./UserResponse";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent{

  recipe!: RecipeResponse;
  hasBeenLikedByTheUser!: boolean;
  hasBeenSubscibed!: boolean;
  currentUser! : UserResponse;


  constructor(private activateRoute: ActivatedRoute, private recipeService: RecipeService,
              private recipeDetailsService: RecipeDetailsService, private location: Location,
              private userSrvice: UserService) {
    this.setData();
  }

  setData(){
    this.recipeDetailsService.selectedRecipe$.subscribe((value: any) => {
      this.recipe = value;
      this.userSrvice.getCurrentUser().subscribe((value: any) => {
        this.currentUser = value;
        if (this.currentUser.subscribedAuthors.includes(this.recipe.authorSub)) {
          this.hasBeenSubscibed = true;
        }else{
          this.hasBeenSubscibed = false;
        }
        if (this.currentUser.likedRecipe.includes(this.recipe.id)) {
          this.hasBeenLikedByTheUser = true;
        }else{
          this.hasBeenLikedByTheUser = false;
        }
      });
    });
  }

  likeRecipe() {
    this.recipeDetailsService.likeRecipe(this.recipe.id).subscribe(data => {
      this.recipeDetailsService.setRecipe(data);
      this.recipeService.triggerRefresh();
      this.userSrvice.triggerCurrentUser();
    });

  }

  dislikeRecipe() {
    this.recipeDetailsService.dislikeRecipe(this.recipe.id).subscribe(data => {
      this.recipeDetailsService.setRecipe(data);
      this.recipeService.triggerRefresh();
      this.userSrvice.triggerCurrentUser();
    });
  }

  goBack() {
    this.location.back();
  }

  subscribeToUser() {
    this.userSrvice.subscribeToUser(this.recipe.authorSub).subscribe(data =>{
      this.hasBeenSubscibed = true;
    });
    this.userSrvice.triggerCurrentUser();
  }

  unsubscribeToUser() {
    this.userSrvice.unsubscribeToUser(this.recipe.authorSub).subscribe(data =>{
      this.hasBeenSubscibed = false;
    });
    this.userSrvice.triggerCurrentUser();
  }
}
