import {Routes} from '@angular/router';
import {NutritionsComponent} from './components/nutritions/nutritions.component';
import {RegisterComponent} from './components/auth/register/register.component';
import {LoginComponent} from './components/auth/login/login.component';
import {
  MealPlannerCalendarComponent
} from './components/mealplan/meal-planner-calendar/meal-planner-calendar.component';
import {PantryListComponent} from './components/pantry/pantry.component';
import {MealPlanListComponent} from './components/mealplan/mealplan.component';
import {RecipeListComponent} from './components/recipes/recipes.component';
import {ShoppingListListComponent} from './components/shopping-list/shopping-list.component';
import {authGuard} from './guards/auth.guard';
import {KitchenListComponent} from './components/kitchen/kitchen.component';
import {IngredientListComponent} from './components/ingredients/ingredients.component';
import {IngredientCategoryListComponent} from './components/ingredient-category/ingredient-category.component';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login',
  },
  {
    path: 'meal-planner',
    component: MealPlannerCalendarComponent,
    canActivate: [authGuard],
    title: 'Meal Planner'
  },
  {
    title: 'Mealplans',
    path: 'meal-plan',
    component: MealPlanListComponent,
    canActivate: [authGuard]
  }, {
    title: 'Kitchens',
    path: 'kitchens',
    component: KitchenListComponent,
    canActivate: [authGuard]
  },
  {
    title: 'Nutritions',
    path: 'nutritions',
    component: NutritionsComponent,
    canActivate: [authGuard]
  },
  {
    title: 'Pantry',
    path: 'pantry',
    component: PantryListComponent,
    canActivate: [authGuard]
  },
  {
    title: 'Recipes',
    path: 'recipes',
    component: RecipeListComponent
  },
  {
    title: 'Shopping List',
    path: 'shopping-list',
    component: ShoppingListListComponent,
    canActivate: [authGuard]
  }, {
    title: 'Ingredients',
    path: 'ingredients',
    component: IngredientListComponent,
    canActivate: [authGuard]
  }, {
    title: 'Ingredient Category',
    path: 'ingredient-categories',
    component: IngredientCategoryListComponent,
    canActivate: [authGuard]
  },
  {
    title: 'Log in',
    path: 'login',
    component: LoginComponent,
  },
  {
    title: 'Register',
    path: 'register',
    component: RegisterComponent,
  },
];
