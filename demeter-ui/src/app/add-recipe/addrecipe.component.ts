import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {RecipeRequest} from "./recipeRequest";
import {Ingredient} from "./ingredient";
import {Router} from "@angular/router";
import {FileSystemFileEntry, NgxFileDropEntry} from "ngx-file-drop";
import {environment} from '../../environments/environment';
import {Step} from "./step";
import {UploadPhotoResponse} from "./uploadPhotoResponse";
import {PhotoService} from "./photo.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CommentRequest} from "../recipe-details/CommentRequest";
import {UserService} from "../recipe-details/user.service";

@Component({
  selector: 'app-addrecipe',
  templateUrl: './addrecipe.component.html',
  styleUrls: ['./addrecipe.component.css']
})
export class AddrecipeComponent {
  public files: NgxFileDropEntry[] = [];
  public fileuploaded: boolean = false;
  selectedImage: File | null = null;
  thumbnail: string | null = null;
  apiURL = environment.apiUrl;

  recipeForm: FormGroup;
  recipeRequest= new RecipeRequest ('', '', '',1,
    [new Ingredient(1, '', '')],
    [new Step(1, '')], 0, 0, 0, 0, new UploadPhotoResponse(0,''), 0, 0, []);
  private uploadedFile: File | undefined;


  constructor(private photoService: PhotoService, private formBuilder: FormBuilder, private http: HttpClient,
              private router: Router, private matSnackBar: MatSnackBar, private userService: UserService) {
    this.recipeForm = this.formBuilder.group({
      name: ['', Validators.required],
      author: ['', Validators.required],
      servingSize: [1, Validators.required],
      method: this.formBuilder.array([], Validators.minLength(1)),
      ingredientList: this.formBuilder.array([], Validators.minLength(1)),
      // price: [0],
      // preparationTime: [0],
      // cookingTime: [0],
      // restingTime: [0]
    });
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

  addIngredient() {
    this.recipeRequest.ingredientList.push({
      quantity: 1,
      measuringUnites: '',
      name: ''
    });
  }

  removeIngredient(index: number) {
    this.recipeRequest.ingredientList.splice(index, 1);
  }

  moveIngredientUp(index: number) {
    if (index > 0) {
      const currentIngredient = { ...this.recipeRequest.ingredientList[index] };
      const previousIngredient = { ...this.recipeRequest.ingredientList[index - 1] };
      this.recipeRequest.ingredientList[index] = previousIngredient;
      this.recipeRequest.ingredientList[index - 1] = currentIngredient;
    }
  }

  moveIngredientDown(index: number) {
    if (index < this.recipeRequest.ingredientList.length - 1) {
      const currentIngredient = { ...this.recipeRequest.ingredientList[index] };
      const nextIngredient = { ...this.recipeRequest.ingredientList[index + 1] };
      this.recipeRequest.ingredientList[index] = nextIngredient;
      this.recipeRequest.ingredientList[index + 1] = currentIngredient;
    }
  }
  public addStep() {
    this.recipeRequest.method.push({
      number: this.recipeRequest.method.length+1,
      text: ''
    });

  }

  moveStepUp(index: number) {
    if (index > 0) {
      const currentStep = { ...this.recipeRequest.method[index]};
      currentStep.number = currentStep.number+1;
      const previousStep = { ...this.recipeRequest.method[index - 1] };
      previousStep.number = previousStep.number-1;
      this.recipeRequest.method[index] = previousStep;
      this.recipeRequest.method[index - 1] = currentStep;
    }
  }

  moveStepDown(index: number) {
    if (index < this.recipeRequest.method.length - 1) {
      const currentStep = { ...this.recipeRequest.method[index] };
      currentStep.number = currentStep.number-1;
      const nextStep = { ...this.recipeRequest.method[index + 1] };
      nextStep.number = nextStep.number+1;
      this.recipeRequest.method[index] = nextStep;
      this.recipeRequest.method[index + 1] = currentStep;
    }
  }

  removeStep(index: number) {
    this.recipeRequest.method.splice(index, 1);
  }
  resetForm() {
    this.recipeForm.reset({
      name: '',
      author: '',
      servingSize: 0,
      ingredientList: [
        {
          quantity: 1,
          measuringUnites: '',
          name: ''
        }
      ],
      method: [
        {
          number: 1,
          text: ''
        }
      ],
      price: 0,
      preparationTime: 0,
      cookingTime: 0,
      restingTime: 0,
      photo: {
        id: '',
        photoUrl: ''
      }
    });
    this.recipeRequest = new RecipeRequest ('', '', '', 1,
      [new Ingredient(1, '', '')],
      [new Step(1, '')], 0, 0, 0, 0, new UploadPhotoResponse(0,''), 0, 0, []);

    this.selectedImage = null;
    this.thumbnail = null;
  }
  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;

    for (const droppedFile of files) {
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {
          const reader = new FileReader();
          reader.onload = (event: any) => {
            this.selectedImage = event.target.result;
          };
          reader.readAsDataURL(file);
          this.thumbnail = this.photoService.generateThumbnail(file);
          this.fileuploaded=true;
          this.uploadedFile = file;
          console.log(this.fileuploaded.valueOf())
        });
      }
    }
  }

  async submitForm() {
    if (this.fileuploaded) {
      await this.uploadPhoto();
    }
    this.recipeRequest.setauthorSub(this.userService.getUserSub())
    this.http.post<RecipeRequest>(this.apiURL.concat('recipes'), this.recipeRequest).subscribe({
      next: response => {
        console.log('Form submitted successfully!', response);
        this.matSnackBar.open("Przepis dodany pomyślnie", "Ok")
        this.resetForm();
      },
      error: error => {
        console.error('An error occurred while submitting the form:', error);
      }
    });
  }

  uploadPhoto(): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      if (this.uploadedFile !== undefined) {
        this.photoService.uploadPhoto(this.uploadedFile).subscribe(data => {
          this.recipeRequest.photo.photoUrl = data.photoUrl;
          this.recipeRequest.photo.id = Number(data.id);
          console.log("Photo added successfully. Photo id: " + this.recipeRequest.photo.id);
          resolve();
        }, error => {
          reject(error);
        });
      } else {
        resolve();
      }
    });
  }

  removeImage() {
    this.selectedImage = null;
    this.thumbnail = null;
  }

  public fileOver(event: any){
    console.log(event);
  }

  public fileLeave(event: any){
    console.log(event);
  }

  public saveSketch(){

  }


}

