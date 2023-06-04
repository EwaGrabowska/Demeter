import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UploadPhotoComponent} from "./upload-photo/upload-photo.component";
import {SaveRecipeDetailsComponent} from "./save-recipe-details/save-recipe-details.component";
import {HomeComponent} from "./home/home.component";
import {MyRecipesComponent} from "./my-recipes/my-recipes.component";
import {AddrecipeComponent} from "./addrecipe/addrecipe.component";


const routes: Routes = [
  {
    path: '', component: HomeComponent,
  },
  {
    path: 'myrecipes', component: MyRecipesComponent,
  },
  {
    path: 'myrecipes/addrecipe', component: AddrecipeComponent,
  },
  {
    path: 'upload-photo', component: UploadPhotoComponent,
  },
  {
    path: 'save-recipe-details/:id', component: SaveRecipeDetailsComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
