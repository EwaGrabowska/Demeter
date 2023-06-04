import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-my-recipes',
  templateUrl: './my-recipes.component.html',
  styleUrls: ['./my-recipes.component.css']
})
export class MyRecipesComponent {
  constructor(private http: HttpClient, private router: Router) {

  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
