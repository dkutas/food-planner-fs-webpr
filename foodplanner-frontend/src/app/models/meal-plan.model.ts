import {Recipe} from './recipe.model';

export interface MealPlan {
  id: string;
  recipe: Recipe;
  startDate: string; // ISO 8601 date string
  endDate: string; // ISO 8601 date string
}
