import {Ingredient} from "./ingredient";
import {Step} from "./step";
import {UploadPhotoResponse} from "./uploadPhotoResponse";
import {CommentRequest} from "../comments/commentRequest";

export class RecipeRequest {

  constructor(
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

  setauthorSub(value: string) {
    this.authorSub = value;
  }

  setSketchValue(value: boolean) {
    this.sketch = value;
  }
  }

