import {IngredientCategory} from './ingredient-category.model';


export interface Ingredient {
  id: string;
  name: string;
  category: IngredientCategory;
}

export interface MissingIngredientByMeal {
  mealPlanId: string;
  recipeId: string;
  ingredientId: string;
}
