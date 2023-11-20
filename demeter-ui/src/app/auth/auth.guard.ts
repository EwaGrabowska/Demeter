import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {UserService} from "../recipe-details/user.service";
import {Injectable} from "@angular/core";
import {OidcSecurityService} from "angular-auth-oidc-client";

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: UserService, private router: Router, private oidcSecurityService: OidcSecurityService,) {}

  async canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean> {
    if (this.authService.isAuthenticated()){
      return true;
    }else {
      this.oidcSecurityService.authorize();

      return false;
    }
  }
}
