import {Component} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  // metoda obsługi zdarzenia kliknięcia na całą kartę
  handleCardClick() {
    // wykonaj akcję po kliknięciu na kartę
    console.log('Kliknięto na kartę!');
    // inne operacje...
  }
  itemsArray = new Array(10); // Tworzy tablicę o długości 10

}
