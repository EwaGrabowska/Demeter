import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {UserResponse} from "./UserResponse";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiURL = environment.apiUrl;

  constructor(private httpClient: HttpClient ) {}

  subscribeToUser(userId: String): Observable<UserResponse>{
    return this.httpClient.post<UserResponse>(this.apiURL.concat("user/subscribe/"+userId), null);
  }

  unsubscribeToUser(){

  }
}
