import { Component } from '@angular/core';
import {OidcSecurityService} from "angular-auth-oidc-client";
import {Router} from "@angular/router";
import {UserService} from "../recipe-details/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  isAuthenticated: boolean = false;
  constructor(private oidcSecurityService: OidcSecurityService, private router: Router, private userService: UserService) {
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

  ngOnInit(): void{
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated}) =>{
      this.isAuthenticated = isAuthenticated
    })
  }

  login() {
    this.oidcSecurityService.authorize();
  }

  logout() {
    this.oidcSecurityService.logoffAndRevokeTokens();
  }
}
