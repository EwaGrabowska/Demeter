import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, catchError, Observable, of} from 'rxjs';
import {RecipeResponse} from "./recipeResponse";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  private apiUrl = environment.apiUrl;
  private recipesSubject$: BehaviorSubject<RecipeResponse[]> = new BehaviorSubject<RecipeResponse[]>([]);
  private refreshInterval: number = 60000;

  constructor(private http: HttpClient) {
    this.startBackgroundRefreshing();
  }

  getAllRecipes(): Observable<RecipeResponse[]> {
    if (this.recipesSubject$.getValue().length > 0) {
      return this.recipesSubject$.asObservable();
    }

    this.http.get<RecipeResponse[]>(`${this.apiUrl}recipes/allrecipes`).subscribe(
      (recipes: RecipeResponse[]) => {
        this.sortAndSetRecipesInCache(recipes);
      },
      (error) => {
        console.error('Error while fetching recipes:', error);
      }
    );

    return this.recipesSubject$.asObservable();
  }

  private startBackgroundRefreshing(): void {
    setInterval(() => {
      this.refreshRecipes();
    }, this.refreshInterval);
  }

  private refreshRecipes(): void {
    this.http.get<RecipeResponse[]>(`${this.apiUrl}recipes/allrecipes`).subscribe(
      (recipes: RecipeResponse[]) => {
        this.sortAndSetRecipesInCache(recipes);
      },
      (error) => {
        console.error('Error while refreshing recipes:', error);
      }
    );
  }
  private sortAndSetRecipesInCache(recipes: RecipeResponse[]): void {
    const sortedRecipes = recipes.sort((a, b) => b.id - a.id);
    this.recipesSubject$.next(sortedRecipes);
  }
}
