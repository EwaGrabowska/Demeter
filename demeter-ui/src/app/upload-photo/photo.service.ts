import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FileSystemFileEntry} from "ngx-file-drop";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private httpClient: HttpClient) { }

  uploadPhoto(fileEntry: File): Observable<any> {
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name)
      return this.httpClient.post("http://localhost:8181/recipes/addphoto", formData);
  }
}
