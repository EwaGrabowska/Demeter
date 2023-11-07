import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UploadPhotoResponse} from "./uploadPhotoResponse";
import {environment} from "../../environments/environment";
import {RecipeRequest} from "./recipeRequest";
import {UploadRecipeResponse} from "./UploadRecipeResponse";

@Injectable({
  providedIn: 'root'
})
export class AddrecipeService {
  thumbnail!: string;
  apiURL = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  uploadPhoto(fileEntry: File): Observable<UploadPhotoResponse> {
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
    return this.httpClient.post<UploadPhotoResponse>(this.apiURL.concat('recipes/addphoto'), formData);
  }

  addRecipe(recipeRequest: RecipeRequest): Observable<UploadRecipeResponse> {
    return this.httpClient.post<UploadRecipeResponse>(this.apiURL.concat('recipes'), recipeRequest);
  }
}
