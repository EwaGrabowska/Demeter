import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UploadPhotoResponse} from "./uploadPhotoResponse";

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private httpClient: HttpClient) { }

  uploadPhoto(fileEntry: File): Observable<UploadPhotoResponse> {
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
      return this.httpClient.post<UploadPhotoResponse>("http://localhost:8080/recipes/addphoto", formData);
  }
}
