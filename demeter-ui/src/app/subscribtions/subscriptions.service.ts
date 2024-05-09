import { Injectable } from '@angular/core';
import {UserService} from "../recipe-details/user.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionsService {
  subscribedUsers: string[] = [];

  constructor(private authService: UserService) {

  }

  getSubscribedAuthors(): string[]{
    this.authService.getCurrentUser().subscribe(user =>{
      if (user){
        this.subscribedUsers = user.subscribedAuthors;
      }

    });
    return this.subscribedUsers;
  }

}
