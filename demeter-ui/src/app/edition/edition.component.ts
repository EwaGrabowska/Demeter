import { Component } from '@angular/core';
import {FileSystemFileEntry, NgxFileDropEntry} from "ngx-file-drop";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {RecipeService} from "../home/recipeService";
import {RecipeDetailsService} from "../recipe-details/recipeDetails.service";
import {Location} from "@angular/common";
import {EditionService} from "./edition.service";
import {RecipeResponse} from "../home/recipeResponse";

@Component({
  selector: 'app-edition',
  templateUrl: './edition.component.html',
  styleUrls: ['./edition.component.css']
})
export class EditionComponent {

  public files: NgxFileDropEntry[] = [];
  public fileuploaded: boolean = false;
  selectedImage: string | undefined;
  thumbnail: string | null = null;
  recipeForm: FormGroup;
  recipeResponse!: RecipeResponse;
  private uploadedFile: File | undefined;


  constructor(private formBuilder: FormBuilder, private router: Router, private matSnackBar: MatSnackBar,
              private recipeService: RecipeService, private editionService: EditionService,
              private recipeDetailsService: RecipeDetailsService, private location: Location) {

    this.recipeDetailsService.selectedRecipe$.subscribe((value: RecipeResponse) => {
      this.recipeResponse = value as RecipeResponse;
    });
    this.recipeForm = this.formBuilder.group({
      name: ['', Validators.required],
      author: ['', Validators.required],
      servingSize: [1, Validators.required],
      method: this.formBuilder.array([], Validators.minLength(1)),
      ingredientList: this.formBuilder.array([], Validators.minLength(1)),
    });
    this.selectedImage = this.recipeResponse.photo.photoUrl;
  }

  addIngredient() {
    this.recipeResponse.ingredientList.push({
      quantity: 1,
      measuringUnits: '',
      name: ''
    });
  }

  removeIngredient(index: number) {
    this.recipeResponse.ingredientList.splice(index, 1);
  }

  moveIngredientUp(index: number) {
    if (index > 0) {
      const currentIngredient = { ...this.recipeResponse.ingredientList[index] };
      const previousIngredient = { ...this.recipeResponse.ingredientList[index - 1] };
      this.recipeResponse.ingredientList[index] = previousIngredient;
      this.recipeResponse.ingredientList[index - 1] = currentIngredient;
    }
  }

  moveIngredientDown(index: number) {
    if (index < this.recipeResponse.ingredientList.length - 1) {
      const currentIngredient = { ...this.recipeResponse.ingredientList[index] };
      const nextIngredient = { ...this.recipeResponse.ingredientList[index + 1] };
      this.recipeResponse.ingredientList[index] = nextIngredient;
      this.recipeResponse.ingredientList[index + 1] = currentIngredient;
    }
  }
  public addStep() {
    this.recipeResponse.method.push({
      number: this.recipeResponse.method.length+1,
      text: ''
    });
  }
  moveStepUp(index: number) {
    if (index > 0) {
      const currentStep = { ...this.recipeResponse.method[index]};
      currentStep.number = currentStep.number+1;
      const previousStep = { ...this.recipeResponse.method[index - 1] };
      previousStep.number = previousStep.number-1;
      this.recipeResponse.method[index] = previousStep;
      this.recipeResponse.method[index - 1] = currentStep;
    }
  }
  moveStepDown(index: number) {
    if (index < this.recipeResponse.method.length - 1) {
      const currentStep = { ...this.recipeResponse.method[index] };
      currentStep.number = currentStep.number-1;
      const nextStep = { ...this.recipeResponse.method[index + 1] };
      nextStep.number = nextStep.number+1;
      this.recipeResponse.method[index] = nextStep;
      this.recipeResponse.method[index + 1] = currentStep;
    }
  }

  removeStep(index: number) {
    this.recipeResponse.method.splice(index, 1);
  }
  delete() {
    this.editionService.deleteSketch(this.recipeResponse)
  }
  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;

    for (const droppedFile of files) {
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          this.selectedImage = file.name;
          this.fileuploaded=true;
          this.uploadedFile = file;
          console.log(this.fileuploaded.valueOf())
        });
      }
    }
  }

  async submitForm() {
    this.recipeResponse.sketch = false;
    if (this.fileuploaded) {
      await this.uploadPhoto();
    }
    this.editionService.saveAsRecipe(this.recipeResponse).subscribe({
      next: response => {
        console.log('Form submitted successfully! Recipe id: ', response);
        this.matSnackBar.open("Przepis dodany pomyślnie", "Ok")
          .afterDismissed().subscribe(()=>{
            this.router.navigate(['/notes']);
          }
        )
      },
      error: error => {
        console.error('An error occurred while submitting the form:', error);
      }
    });
  }

  uploadPhoto(): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      if (this.uploadedFile !== undefined) {
        this.editionService.uploadPhoto(this.uploadedFile).subscribe(data => {
          this.recipeResponse.photo.photoUrl = data.photoUrl;
          this.recipeResponse.photo.id = Number(data.id);
          console.log("Photo added successfully. Photo id: " + this.recipeResponse.photo.id);
          resolve();
        }, function (error) {
          reject(error);
        });
      } else {
        resolve();
      }
    });
  }
  removeImage() {
    this.selectedImage = undefined;
    this.thumbnail = null;
  }

  async saveSketch() {
    if (this.fileuploaded) {
      await this.uploadPhoto();
    }

    this.editionService.editSketch(this.recipeResponse).subscribe({
      next: response => {
        console.log('Sketch addet! Sketch id: ', response);
        this.matSnackBar.open("Szkic zapisany pomyślnie", "Ok")
          .afterDismissed().subscribe(()=>{
            this.router.navigate(['/notes']);
          }
        )
      },
      error: error => {
        console.error('An error occurred while submitting the form:', error);
      }
    });
  }
}
