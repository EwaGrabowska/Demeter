import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {RecipeResponse} from "./recipeResponse";
import {Subscription} from "rxjs";
import {MatPaginator, MatPaginatorIntl, PageEvent} from "@angular/material/paginator";
import {PolishMatPaginatorIntl} from "./polishMatPaginatorIntl";
import {RecipePageService} from "./recipe-page.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [
    { provide: MatPaginatorIntl, useValue: new PolishMatPaginatorIntl() }
  ]
})
export class HomeComponent implements OnDestroy, OnInit{
  recipes: Array<RecipeResponse> = [];
  getAllRecipeSubscription!: Subscription;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  length = 0;
  pageSize = 2;
  pageIndex = 0;
  pageSizeOptions = [2, 9, 16];
  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  constructor(private recipePageService: RecipePageService) { }

  ngOnInit(): void {
    this.getAllRecipeSubscription = this.recipePageService.getAllRecipesPega(this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.recipes = data.content;
        this.length = data.totalElements;
      });
  }

  ngOnDestroy(): void {
    this.getAllRecipeSubscription.unsubscribe();
  }

  handlePageEvent(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;

    this.recipePageService.getAllRecipesPega(this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.recipes = data.content;
        this.length = data.totalElements;
      });
  }
}
