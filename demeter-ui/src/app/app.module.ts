import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NgxFileDropModule} from "ngx-file-drop";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
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
import { AddrecipeComponent } from './add-recipe/addrecipe.component';
import {MatInputModule} from "@angular/material/input";
import {CommonModule} from "@angular/common";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { AuthConfigModule } from './auth/auth-config.module';
import {AuthInterceptor, AuthModule} from "angular-auth-oidc-client";
import { RecipeDetailsComponent } from './recipe-details/recipe-details.component';
import {RouterModule} from "@angular/router";
import { HistoryComponent } from './history/history.component';
import { SubscribtionsComponent } from './subscribtions/subscribtions.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { LikedRecipeComponent } from './liked-recipe/liked-recipe.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import { MenuComponent } from './menu/menu.component';
import {MatListModule} from "@angular/material/list";
import {MatLineModule} from "@angular/material/core";
import { NotesComponent } from './notes/notes.component';
import { RecipeCardComponent } from './recipe-card/recipe-card.component';
import {MatChipsModule} from "@angular/material/chips";
import {MatLegacyChipsModule} from "@angular/material/legacy-chips";
import { CallbackComponent } from './callback/callback.component';
import { CommentsComponent } from './comments/comments.component';
import {MatMenuModule} from "@angular/material/menu";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatPaginatorModule} from "@angular/material/paginator";
import {ErrorInterceptor} from "./errorInterceptor";
import { SingleSubscriptionComponent } from './single-subscription/single-subscription.component';
import { EditionComponent } from './edition/edition.component';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    MyRecipesComponent,
    AddrecipeComponent,
    RecipeDetailsComponent,
    HistoryComponent,
    SubscribtionsComponent,
    SidebarComponent,
    LikedRecipeComponent,
    MenuComponent,
    NotesComponent,
    RecipeCardComponent,
    CallbackComponent,
    CommentsComponent,
    SingleSubscriptionComponent,
    EditionComponent,
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
        FlexLayoutModule,
        MatSnackBarModule,
        AuthConfigModule,
        RouterModule,
        MatSidenavModule,
        MatListModule,
        MatLineModule,
        MatChipsModule,
        MatLegacyChipsModule,
        MatMenuModule,
        MatSlideToggleModule,
        MatPaginatorModule,
        AuthModule
    ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ]
})
export class AppModule { }
