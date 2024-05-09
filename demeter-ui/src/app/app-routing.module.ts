import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {MyRecipesComponent} from "./my-recipes/my-recipes.component";
import {AddrecipeComponent} from "./add-recipe/addrecipe.component";
import {MenuComponent} from "./menu/menu.component";
import {NotesComponent} from "./notes/notes.component";
import {HistoryComponent} from "./history/history.component";
import {LikedRecipesComponent} from "./liked-recipes/liked-recipes.component";
import {SubscribtionsComponent} from "./subscribtions/subscribtions.component";
import {RecipeDetailsComponent} from "./recipe-details/recipe-details.component";
import {CallbackComponent} from "./callback/callback.component";
import {AuthGuard} from "./auth/auth.guard";
import {EditionComponent} from "./edition/edition.component";
import {ProfileEditComponent} from "./profile-edit/profile-edit.component";


const routes: Routes = [

  {
    path: '', component: MenuComponent,
    children: [
      {
        path: '', component: HomeComponent
      },
      {
        path: 'myrecipes', component: MyRecipesComponent, canActivate: [AuthGuard]
      },
      {
        path: 'addrecipe', component: AddrecipeComponent, canActivate: [AuthGuard]
},
      {
        path: 'notes', component: NotesComponent, canActivate: [AuthGuard]
      },
      {
        path: 'history', component: HistoryComponent, canActivate: [AuthGuard]
      },
      {
        path: 'likedrecipe', component: LikedRecipesComponent, canActivate: [AuthGuard]
      },
      {
        path: 'subscribtions', component: SubscribtionsComponent, canActivate: [AuthGuard]
      },
      {
        path: 'details', component: RecipeDetailsComponent,
      },
      {
        path: 'edition', component: EditionComponent, canActivate: [AuthGuard]
      },
      {
        path: 'editprofile', component: ProfileEditComponent, canActivate: [AuthGuard]
      }
    ]
  },
  {
    path: 'callback', component: CallbackComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
