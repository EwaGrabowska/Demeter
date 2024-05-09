import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";
import {Subscription} from "rxjs";
import {MatPaginator, MatPaginatorIntl, PageEvent} from "@angular/material/paginator";
import {PolishMatPaginatorIntl} from "../home/polishMatPaginatorIntl";
import {MyRecipesPageService} from "./my-recipes-page.service";

@Component({
  selector: 'app-my-recipes',
  templateUrl: './my-recipes.component.html',
  styleUrls: ['./my-recipes.component.css'],
  providers: [
    { provide: MatPaginatorIntl, useValue: new PolishMatPaginatorIntl() }
  ]
})
export class MyRecipesComponent implements OnDestroy, OnInit{
  parentComponent: string = 'myRecipes';
  myRecipes: Array<RecipeResponse> = [];
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

  constructor(private recipePageService: MyRecipesPageService) { }

  ngOnInit(): void {
    this.getAllRecipeSubscription = this.recipePageService.getMyRecipes(this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.myRecipes = data.content;
        this.length = data.totalElements;
      });
  }

  ngOnDestroy(): void {
    this.getAllRecipeSubscription.unsubscribe();
  }

  handlePageEvent(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;

    this.recipePageService.getMyRecipes(this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.myRecipes = data.content;
        this.length = data.totalElements;
      });
  }
}
