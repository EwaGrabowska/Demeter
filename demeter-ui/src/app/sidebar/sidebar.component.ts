import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  shouldRun: boolean = true;
  constructor(private router: Router){
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

}
