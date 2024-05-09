import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {UserResponse} from "./UserResponse";
import {Observable, BehaviorSubject, retry} from "rxjs";
import {OidcSecurityService} from "angular-auth-oidc-client";
import {Router} from "@angular/router";
import {Userupdaterequest} from "../profile-edit/userupdaterequest";
import {UserPhotoUrl} from "../profile-edit/user-photo-url";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiURL = environment.apiUrl;
  private currentUser: BehaviorSubject<UserResponse | null> = new BehaviorSubject<UserResponse | null>(null);
  private currentUserSub: string = "";
  private photoURL: String | undefined = '';
  isAuthenticatedValue: boolean = false;
  isAuthenticatedSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private uploadedFile: File | undefined;
  constructor(private httpClient: HttpClient, private oidcSecurityService: OidcSecurityService, private router: Router) {}

  registerUser() {
    return this.httpClient.get<UserResponse>(this.apiURL.concat("user/register")).subscribe(data =>{
      this.currentUser.next(data)
      this.currentUserSub = data.sub;
      this.photoURL = data.picture;
      this.isAuthenticatedSubject.next(true)
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
  isAuthenticated(): boolean {
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated}) =>{
      this.isAuthenticatedValue = isAuthenticated;
      this.triggerCurrentUser();
    })
    return this.isAuthenticatedValue;
  }
  logout() {
    this.currentUser.next(null);
    this.isAuthenticatedSubject.next(false)
    this.router.navigate(['/'])
  }

  async updateUser(picture: string | undefined | File, fullName: string): Promise<void> {
    let userupdaterequest: Userupdaterequest = new Userupdaterequest(fullName, this.currentUserSub, '')

    if (picture instanceof File) {
      this.uploadedFile = picture;
      const photoURL = await this.sendingPhotoToBackend(this.uploadedFile).toPromise();
      this.photoURL = photoURL?.picture;
      if (this.photoURL) {
        userupdaterequest.setPhotoURL(this.photoURL.valueOf());
      }
    }else{
      if (this.photoURL){
        userupdaterequest.setPhotoURL(this.photoURL.valueOf());
      }
    }
    await this.httpClient.post<UserResponse>(this.apiURL.concat("user/update"), userupdaterequest).toPromise();
  }

  sendingPhotoToBackend(fileEntry: File): Observable<UserPhotoUrl> {
    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);
    return this.httpClient.post<UserPhotoUrl>(this.apiURL.concat("user/updatephoto"), formData);
  }
}
