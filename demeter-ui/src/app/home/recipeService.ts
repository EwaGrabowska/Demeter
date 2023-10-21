import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {RecipeResponse} from "./recipeResponse";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class RecipeService {
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

  getAllRecipes(): Observable<RecipeResponse[]> {
    this.refreshTrigger$.subscribe(() => {
      this.http.get<RecipeResponse[]>(`${this.apiUrl}recipes/allrecipes`).subscribe(
        (recipes: RecipeResponse[]) => {
          this.sortAndSetRecipesInCache(recipes);
        },
        (error) => {
          console.error('Error while fetching recipes:', error);
        }
      );
    });

    return this.recipesSubject$.asObservable();
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
