import { Injectable } from '@angular/core';
import {CommentRequest} from "./commentRequest";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {RecipeResponse} from "../home/recipeResponse";
import {CommentResponse} from "./commentResponse";

@Injectable({
  providedIn: 'root'
})
export class CommentsService {
  apiURL = environment.apiUrl;
  constructor(private httpClient: HttpClient) { }

  addComment(commentRequest: CommentRequest, recipeId: string): Observable<any> {
    return this.httpClient.post<any>(this.apiURL.concat("recipes/"+recipeId+"/comment"), commentRequest);
  }

  getAllComments(recipeId: string): Observable<CommentResponse[]> {
    return this.httpClient.get<CommentResponse[]>(this.apiURL.concat("recipes/"+recipeId+"/comment"));
  }
}
