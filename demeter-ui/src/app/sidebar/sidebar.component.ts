import {Component, OnInit} from "@angular/core";
import {UserService} from "../recipe-details/user.service";


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit{
  isLoggedIn!: boolean;
  constructor(private authService: UserService){
  }
  ngOnInit() {
    this.authService.isAuthenticatedSubject.subscribe(value => {
      if (value){
        this.isLoggedIn = true;
      }else {
        this.isLoggedIn = false;
      }
    })
  }

  isUserLoggedIn(): boolean {
    return this.isLoggedIn;
  }
}
