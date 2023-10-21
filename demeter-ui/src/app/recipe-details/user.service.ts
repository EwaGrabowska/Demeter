import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {UserResponse} from "./UserResponse";
import {Observable, BehaviorSubject} from "rxjs";
import {OidcSecurityService} from "angular-auth-oidc-client";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiURL = environment.apiUrl;
  private currentUser: BehaviorSubject<UserResponse | null> = new BehaviorSubject<UserResponse | null>(null);
  private currentUserSub: string = "";
  private token!: string;

  constructor(private httpClient: HttpClient, private oidcSecurityService: OidcSecurityService ) {}

  registerUser() {
    return this.httpClient.get<UserResponse>(this.apiURL.concat("user/register")).subscribe(data =>{
      this.currentUser.next(data)
      this.currentUserSub = data.sub;
    });
  }
  triggerCurrentUser() {
    this.registerUser();
  }
  subscribeToUser(subscribedSub: string): Observable<UserResponse>{
    let params = new HttpParams();
    params = params.append('subscribedsub', subscribedSub);
    params = params.append('currentsub', this.currentUserSub);
    return this.httpClient.post<UserResponse>(this.apiURL.concat("user/subscribe"), null, { params: params });
  }

  unsubscribeToUser(subscribedSub: string): Observable<UserResponse>{
    let params = new HttpParams();
    params = params.append('subscribedsub', subscribedSub);
    params = params.append('currentsub', this.currentUserSub);
    return this.httpClient.post<UserResponse>(this.apiURL.concat("user/unsubscribe"), null, { params: params });

  }



  getUserSub(): string{
    return this.currentUserSub;
  }

  getCurrentUser(): Observable<UserResponse | null> {
    return this.currentUser.asObservable();
  }
}
