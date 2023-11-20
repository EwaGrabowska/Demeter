import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {MyRecipesComponent} from "./my-recipes/my-recipes.component";
import {AddrecipeComponent} from "./add-recipe/addrecipe.component";
import {MenuComponent} from "./menu/menu.component";
import {NotesComponent} from "./notes/notes.component";
import {HistoryComponent} from "./history/history.component";
import {LikedRecipeComponent} from "./liked-recipe/liked-recipe.component";
import {SubscribtionsComponent} from "./subscribtions/subscribtions.component";
import {RecipeDetailsComponent} from "./recipe-details/recipe-details.component";
import {CallbackComponent} from "./callback/callback.component";
import {AuthGuard} from "./auth/auth.guard";


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
        path: 'likedrecipe', component: LikedRecipeComponent, canActivate: [AuthGuard]
      },
      {
        path: 'subscribtions', component: SubscribtionsComponent, canActivate: [AuthGuard]
      },
      {
        path: 'details', component: RecipeDetailsComponent,
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
