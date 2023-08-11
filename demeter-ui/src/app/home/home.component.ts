import {Component, OnInit} from '@angular/core';
import {RecipeResponse} from "./recipeResponse";
import {RecipeService} from "./recipeService";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  selectedItemId: string | null = null;
  selectedItem: RecipeResponse | undefined;
  itemsArray: RecipeResponse[] = [];
  recipeAvailable?: boolean;
  constructor(private router: Router, private recipeService: RecipeService, private activatedRoute: ActivatedRoute) {

  }
  ngOnInit(): void {
    this.recipeAvailable = false;
    this.selectedItemId = null;
    this.getAllRecipes();
  }
  private updateSelectedItem(): void {
    if (this.selectedItemId == null) {
      this.selectedItem = this.itemsArray[0];
      this.recipeAvailable = true;
    } else {
      this.selectedItem = this.itemsArray.find(item => item.id.toString() == this.selectedItemId);
      this.recipeAvailable = true;
    }
  }
  getAllRecipes(): void {
    this.recipeService.getAllRecipes().subscribe(
      (recipes: RecipeResponse[]) => {
        this.itemsArray = recipes;
        this.selectedItemId = this.activatedRoute.snapshot.paramMap.get('id');
        this.updateSelectedItem()
      },
      (error) => {
        console.error('Error while fetching recipes:', error);
      }
    );
  }

  handleCardClick(itemId: number) {
    console.log('Item id=', itemId);
    this.selectedItemId = itemId.toString();
    this.selectedItem = this.itemsArray.find(item => item.id.toString() == this.selectedItemId);
    this.router.navigate(['/', itemId]);
  }

}
