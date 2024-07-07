import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {RecipeRequest} from "../add-recipe/recipeRequest";
import {ValidationResponse} from "./validation-response";

@Injectable({
  providedIn: 'root'
})
export class RecipeConverterService {
  apiURL = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  validateText(text: string): Observable<ValidationResponse> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<ValidationResponse>(this.apiURL.concat("openiaapi/validate"), {text}, {headers});
  }

  convertText(text: string): Observable<RecipeRequest> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<RecipeRequest>(this.apiURL.concat("openiaapi/convert"), {text}, {headers});
  }
}
