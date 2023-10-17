import {Component, OnInit} from '@angular/core';
import {RecipeService} from "../home/recipeService";
import {RecipeResponse} from "../home/recipeResponse";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit{

  recipes!: RecipeResponse[];

  constructor(private recipeService: RecipeService) {
  }

  ngOnInit(): void {
    this.recipeService.getAllRecipes().subscribe(data =>{
      this.recipes = data;
    })
  }

}
