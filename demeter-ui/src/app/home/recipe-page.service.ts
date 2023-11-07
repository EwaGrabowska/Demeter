import { Injectable } from '@angular/core';
import {RecipeResponse} from "./recipeResponse";
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {RecipePage} from "./recipePage";

@Injectable({
  providedIn: 'root'
})
export class RecipePageService {
  private apiUrl = environment.apiUrl;
  private recipesSubject$: BehaviorSubject<RecipeResponse[]> = new BehaviorSubject<RecipeResponse[]>([]);
  private refreshInterval: number = 60000;
  private refreshTrigger$: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);

  constructor(private http: HttpClient) {
    this.startBackgroundRefreshing();
  }

  triggerRefresh(): void {
    this.refreshTrigger$.next(undefined);
  }

  getAllRecipesPega(pageIndex: number, pageSize: number): Observable<any> {
    const params = new HttpParams()
      .set('page', pageIndex.toString())
      .set('size', pageSize.toString());

    return this.http.get<RecipePage<RecipeResponse>>(`${this.apiUrl}recipes/paginated`, {params});
  }

  private startBackgroundRefreshing(): void {
    setInterval(() => {
      this.triggerRefresh();
    }, this.refreshInterval);
  }

  private sortAndSetRecipesInCache(recipes: RecipeResponse[]): void {
    const sortedRecipes = recipes.sort((a, b) => b.id - a.id);
    this.recipesSubject$.next(sortedRecipes);
  }
}
