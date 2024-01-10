import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {UserResponse} from "../recipe-details/UserResponse";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {RecipePage} from "../home/recipePage";
import {RecipeResponse} from "../home/recipeResponse";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionsPageService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getUserBySub(userSub: string): Observable<UserResponse> {
    const params = new HttpParams()
      .set('usersub', userSub);

    return this.http.get<UserResponse>(`${this.apiUrl}user/findbysub`, {params});
  }

  getSubscribedUserRecipe(pageSize: number, pageNumber: number, userSub: string): Observable<any>{
    const params = new HttpParams()
      .set('page', pageNumber.toString())
      .set('size', pageSize.toString())
      .set('usersub', userSub);

    return this.http.get<RecipePage<RecipeResponse>>(`${this.apiUrl}recipes/myrecipes/paginated`, {params});
  }
}
