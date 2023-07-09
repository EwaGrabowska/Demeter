import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UploadPhotoResponse} from "./uploadPhotoResponse";
import {environment} from "../../environments/environments";

@Injectable({
  providedIn: 'root'
})
export class PhotoService {
  thumbnail: string | null = null;
  apiURL = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  uploadPhoto(fileEntry: File): Observable<UploadPhotoResponse> {
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
      return this.httpClient.post<UploadPhotoResponse>(this.apiURL.concat('recipes/addphoto'), formData);
  }


  generateThumbnail(file: File) {
    const reader = new FileReader();
    reader.onload = (event: any) => {
      const image = new Image();
      image.src = event.target.result;
      image.onload = () => {
        const canvas = document.createElement('canvas');
        const context = canvas.getContext('2d');
        const maxWidth = 100;
        const maxHeight = 100;
        let width = image.width;
        let height = image.height;

        if (width > height) {
          if (width > maxWidth) {
            height *= maxWidth / width;
            width = maxWidth;
          }
        } else {
          if (height > maxHeight) {
            width *= maxHeight / height;
            height = maxHeight;
          }
        }

        canvas.width = width;
        canvas.height = height;
        if (context) {
          context.drawImage(image, 0, 0, width, height);
          this.thumbnail = canvas.toDataURL('image/jpeg');
        } else {
          console.error('Unable to obtain canvas context.');
        }
        this.thumbnail = canvas.toDataURL('image/jpeg');
      };
    };
    reader.readAsDataURL(file);
    return this.thumbnail;
  }

}
