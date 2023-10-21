import {Component, OnInit} from '@angular/core';
import {UserService} from "../recipe-details/user.service";
import {Router} from "@angular/router";
import {OidcSecurityService} from "angular-auth-oidc-client";

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.css']
})
export class CallbackComponent implements OnInit{

  constructor(private userService: UserService, private router: Router, private oidcSecurityService: OidcSecurityService) {
    this.oidcSecurityService.isAuthenticated$.subscribe(data =>{
      this.userService.registerUser();
      this.router.navigateByUrl('');
    });
  }

  ngOnInit(): void {

  }

}
