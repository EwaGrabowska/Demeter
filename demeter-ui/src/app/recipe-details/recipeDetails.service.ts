import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {RecipeResponse} from "../home/recipeResponse";

@Injectable({
  providedIn: 'root'
})
export class RecipeDetailsService {
  private recipe$ = new BehaviorSubject<any>({});
  selectedRecipe$ = this.recipe$.asObservable();
  private componentName$ = new BehaviorSubject<any>({});
  temporaryParentComponent$ = this.componentName$.asObservable();
  apiURL = environment.apiUrl;
  constructor(private httpClient: HttpClient) {}

  setRecipe(product: any) {
    this.recipe$.next(product);
  }

  likeRecipe(recipeId: Number): Observable<RecipeResponse> {
    return this.httpClient.post<RecipeResponse>(this.apiURL.concat("recipes/"+recipeId+"/like"), null);
  }

  dislikeRecipe(recipeId: Number): Observable<RecipeResponse> {
    return this.httpClient.post<RecipeResponse>(this.apiURL.concat("recipes/"+recipeId+"/dislike"), null);
  }

  setParentConponent(parentComponent: string) {
    this.componentName$.next(parentComponent);
  }
}
