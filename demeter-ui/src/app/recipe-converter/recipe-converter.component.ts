import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RecipeConverterService} from "./recipe-converter.service";
import {MatDialog} from "@angular/material/dialog";
import {ProgressDialogComponent} from "../progress-dialog/progress-dialog.component";
import {RecipeRequest} from "../add-recipe/recipeRequest";
import {Ingredient} from "../add-recipe/ingredient";
import {Step} from "../add-recipe/step";
import {UploadPhotoResponse} from "../add-recipe/uploadPhotoResponse";

@Component({
  selector: 'app-recipe-converter',
  templateUrl: './recipe-converter.component.html',
  styleUrls: ['./recipe-converter.component.css']
})
export class RecipeConverterComponent {
    @Output()
    valueChanged = new EventEmitter<RecipeRequest>();
    recipeForm: FormGroup;
    text: string ='';
    recipeRequest: RecipeRequest = new RecipeRequest ('', '', '',1,
        [new Ingredient(1, '', '')],
        [new Step(1, '')], 0, 0, 0, 0, new UploadPhotoResponse(0,''), 0, 0, [], false);
    constructor(private dialog: MatDialog, private formBuilder: FormBuilder, private converterService: RecipeConverterService) {
        this.recipeForm = this.formBuilder.group({
            text: ['', Validators.required]})
    }

    submitForm() {
        const dialogRef = this.dialog.open(ProgressDialogComponent, {
            disableClose: true,
            data: { message: 'Trwa sprawdzanie tekstu',
                    showSpinner: true },
        });

        this.converterService.validateText(this.text).subscribe(response => {
            if (response.validationResult) {
                dialogRef.componentInstance.data.message = 'Trwa konwertowanie na przepis';
                this.converterService.convertText(this.text).subscribe(recipeRequest => {
                    this.recipeRequest = recipeRequest;
                    this.valueChanged.emit(this.recipeRequest)
                    console.log('json:' + recipeRequest.name);
                    dialogRef.close();
                });
            } else {
                dialogRef.componentInstance.data.message = 'Walidacja tekstu nie przebiegła pomyślnie: ' + response.message
                dialogRef.componentInstance.data.showSpinner = false;
                this.resetForm();
            }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result && result.canceled) {
                console.log('Operacja została anulowana');
            }
        });
    }

    resetForm() {
        this.recipeForm.reset(
            {text: ''}
        )
        this.text = '';
        this.recipeRequest = new RecipeRequest ('', '', '',1,
            [new Ingredient(1, '', '')],
            [new Step(1, '')], 0, 0, 0, 0, new UploadPhotoResponse(0,''), 0, 0, [], false);
        this.valueChanged.emit(this.recipeRequest);
    }
}
