import {Ingredient} from "../add-recipe/ingredient";
import {Step} from "../add-recipe/step";
import {UploadPhotoResponse} from "../add-recipe/uploadPhotoResponse";
import {CommentRequest} from "../comments/commentRequest";

export class RecipeResponse{
  constructor(
    public id: number,
    public name: string,
    public author: string,
    public authorSub: string,
    public servingSize: number,
    public ingredientList: Ingredient[],
    public method: Step[],
    public price: number,
    public preparationTime: number,
    public cookingTime: number,
    public restingTime: number,
    public photo: UploadPhotoResponse,
    public likes: number,
    public disLikes: number,
    public comments: CommentRequest[],
    public sketch: boolean
  ){}

  markAsRecipe() {
    this.sketch = false;
    return this;
  }
}
