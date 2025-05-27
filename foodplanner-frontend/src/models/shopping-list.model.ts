import {Ingredient} from './ingredient.model';

export interface ShoppingList {
  id: string;
  ingredient: Ingredient;
}


export interface ShoppingListInput {
  ingredientId: string;
}
