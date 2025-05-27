import {Ingredient} from './ingredient.model';

export interface Pantry {
  id: string;
  ingredient: Ingredient;
}

export interface PantryInput {
  ingredientId: string;
}
