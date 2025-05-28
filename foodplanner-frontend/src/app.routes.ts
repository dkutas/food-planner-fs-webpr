import {Routes} from '@angular/router';
import {NutritionsComponent} from './components/nutritions/nutritions.component';
import {
  MealPlannerCalendarComponent
} from './components/mealplan/meal-planner-calendar/meal-planner-calendar.component';
import {PantryListComponent} from './components/pantry/pantry.component';
import {MealPlanListComponent} from './components/mealplan/mealplan.component';
import {RecipeListComponent} from './components/recipes/recipes.component';
import {ShoppingListListComponent} from './components/shopping-list/shopping-list.component';
import {authGuard} from './guards/auth.guard';

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
  },
];
