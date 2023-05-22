import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UploadPhotoComponent} from "./upload-photo/upload-photo.component";


const routes: Routes = [
  {
    path: 'upload-photo', component: UploadPhotoComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
