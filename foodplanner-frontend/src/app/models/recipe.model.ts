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
