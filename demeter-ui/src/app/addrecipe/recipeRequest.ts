import {Ingredient} from "./ingredient";

export class RecipeRequest {

  constructor(
    public name: string,
    public author: string,
    public servingSize: number,
    public ingredientList: Ingredient[],
    public method: string,
    public price: number,
    public preparationTime: number,
    public cookingTime: number,
    public restingTime: number) {
  }
}
