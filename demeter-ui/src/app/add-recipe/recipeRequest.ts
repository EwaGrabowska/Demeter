import {Ingredient} from "./ingredient";
import {Step} from "./step";
import {UploadPhotoResponse} from "./uploadPhotoResponse";
import {CommentRequest} from "../recipe-details/CommentRequest";

export class RecipeRequest {

  constructor(
    public name: string,
    public author: string,
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
    public comments: CommentRequest[]
   ){}

  }

