import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";
import {MatPaginator, MatPaginatorIntl, PageEvent} from "@angular/material/paginator";
import {PolishMatPaginatorIntl} from "../home/polishMatPaginatorIntl";
import {Subscription} from "rxjs";
import {MyRecipesPageService} from "../my-recipes/my-recipes-page.service";
import {LikedRecipePageService} from "./liked-recipe-page.service";

@Component({
  selector: 'app-liked-recipe',
  templateUrl: './liked-recipe.component.html',
  styleUrls: ['./liked-recipe.component.css'],
  providers: [
    { provide: MatPaginatorIntl, useValue: new PolishMatPaginatorIntl() }
  ]
})
export class LikedRecipeComponent  implements OnDestroy, OnInit{
  likedRecipes: Array<RecipeResponse> = [];
  getAllRecipeSubscription!: Subscription;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  length = 0;
  pageSize = 9;
  pageIndex = 0;
  pageSizeOptions = [9, 16];
  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;
  userSub!: string;

  constructor(private recipePageService: LikedRecipePageService) { }

  ngOnInit(): void {
    this.getAllRecipeSubscription = this.recipePageService.getLikedRecipes(this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.likedRecipes = data.content;
        this.length = data.totalElements;
      });
  }

  ngOnDestroy(): void {
    this.getAllRecipeSubscription.unsubscribe();
  }

  handlePageEvent(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;

    this.recipePageService.getLikedRecipes(this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.likedRecipes = data.content;
        this.length = data.totalElements;
      });
  }
}
