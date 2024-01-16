import { Injectable } from '@angular/core';
import {RecipeResponse} from "../home/recipeResponse";
import {environment} from "../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {UploadRecipeResponse} from "../add-recipe/UploadRecipeResponse";
import {UploadPhotoResponse} from "../add-recipe/uploadPhotoResponse";

@Injectable({
  providedIn: 'root'
})
export class EditionService {

  apiURL = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  deleteSketch(recipeResponse: RecipeResponse): Observable<Object> {
    const params = new HttpParams()
      .set('id', recipeResponse.id);
    return this.httpClient.delete<string>(this.apiURL.concat('recipes/delete'), {params});
  }

  editSketch(recipeResponse: RecipeResponse): Observable<UploadRecipeResponse> {
    return this.saveRecipe(recipeResponse)
  }

  uploadPhoto(fileEntry: File): Observable<UploadPhotoResponse> {
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
    return this.httpClient.post<UploadPhotoResponse>(this.apiURL.concat('recipes/addphoto'), formData);
  }

  saveAsRecipe(recipeResponse: RecipeResponse): Observable<UploadRecipeResponse> {
    let recipeToSafe: RecipeResponse = recipeResponse.markAsRecipe();
    return this.saveRecipe(recipeToSafe)
  }

  private saveRecipe(recipeResponse: RecipeResponse): Observable<UploadRecipeResponse> {
    return this.httpClient.put<UploadRecipeResponse>(this.apiURL.concat('recipes/edition'), recipeResponse);
  }
}
