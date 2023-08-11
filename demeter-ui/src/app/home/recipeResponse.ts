import {Ingredient} from "../addrecipe/ingredient";
import {Step} from "../addrecipe/step";
import {UploadPhotoResponse} from "../addrecipe/uploadPhotoResponse";

export class RecipeResponse{
  constructor(
    public id: number,
    public name: string,
    public author: string,
    public servingSize: number,
    public ingredientList: Ingredient[],
    public method: Step[],
    public price: number,
    public preparationTime: number,
    public cookingTime: number,
    public restingTime: number,
    public photo: UploadPhotoResponse
  ){}
}
