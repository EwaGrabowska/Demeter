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


const routes: Routes = [

  {
    path: '', component: MenuComponent,
    children: [
      {
        path: '', component: HomeComponent,
      },
      {
        path: 'myrecipes', component: MyRecipesComponent,
      },
      {
        path: 'addrecipe', component: AddrecipeComponent,
      },
      {
        path: 'notes', component: NotesComponent,
      },
      {
        path: 'history', component: HistoryComponent,
      },
      {
        path: 'likedrecipe', component: LikedRecipeComponent,
      },
      {
        path: 'subscribtions', component: SubscribtionsComponent,
      },
      {
        path: 'details', component: RecipeDetailsComponent,
      }
    ]
  },
  {
    path: '', component: HomeComponent,
  },
  {
    path: ':id', component: HomeComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
