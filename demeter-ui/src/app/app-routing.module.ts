import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {MyRecipesComponent} from "./my-recipes/my-recipes.component";
import {AddrecipeComponent} from "./addrecipe/addrecipe.component";
import {RecipeDetailsComponent} from "./recipe-details/recipe-details.component";


const routes: Routes = [
  {
    path: '', component: HomeComponent,
  },
  {
    path: ':id', component: HomeComponent,
  },
  {
    path: 'myrecipes', component: MyRecipesComponent,
  },
  {
    path: 'myrecipes/addrecipe', component: AddrecipeComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
