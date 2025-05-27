import {Ingredient} from './ingredient.model';
import {Kitchen} from './kitchen.model';
import {MealPlan} from './meal-plan.model';

export interface Recipe {
  id: string;
  name: string;
  description?: string;
  kitchen: Kitchen;
  ingredients: Ingredient[];
  mealPlans: MealPlan[];
}

export interface RecipeInput extends Omit<Recipe, 'ingredients' | 'mealPlans' | 'kitchen'> {
  ingredientIds: string[]; // Array of ingredient IDs
  kitchenId: string; // Kitchen ID
  mealPlanIds?: string[]; // Optional array of meal plan IDs
}
