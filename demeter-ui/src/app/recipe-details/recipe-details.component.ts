import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent {

  recipeId!: string;

  constructor(private activateRoute: ActivatedRoute) {
    // this.recipeId = this.activateRoute.snapshot.params.id;
  }

}
