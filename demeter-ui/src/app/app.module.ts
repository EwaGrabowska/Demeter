import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NgxFileDropModule} from "ngx-file-drop";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule} from "@angular/material/button";
import { HeaderComponent } from './header/header.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {FlexLayoutModule} from "@angular/flex-layout";
import { HomeComponent } from './home/home.component';
import {MatSliderModule} from "@angular/material/slider";
import { MyRecipesComponent } from './my-recipes/my-recipes.component';
import {MatCardModule} from "@angular/material/card";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { AddrecipeComponent } from './addrecipe/addrecipe.component';
import {MatInputModule} from "@angular/material/input";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    MyRecipesComponent,
    AddrecipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    NgxFileDropModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatSliderModule,
    MatCardModule,
    MatButtonToggleModule,
    MatInputModule,
    CommonModule,
    ReactiveFormsModule,
    MatButtonToggleModule,
    MatInputModule,
    NgxFileDropModule,
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
