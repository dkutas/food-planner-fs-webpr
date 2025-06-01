import {Routes} from '@angular/router';
import {PantryListComponent} from './components/pantry/pantry.component';
import {MealPlanListComponent} from './components/mealplan/mealplan.component';
import {RecipeListComponent} from './components/recipes/recipes.component';
import {ShoppingListListComponent} from './components/shopping-list/shopping-list.component';
import {authGuard} from './guards/auth.guard';
import {
  MealPlanSchedulerComponent
} from './components/mealplan/meal-plan-scheduler/meal-plan-scheduler.component';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login',
  },
  {
    path: 'meal-plan-scheduler',
    component: MealPlanSchedulerComponent,
    canActivate: [authGuard],
    title: 'Meal Plan Scheduler'
  },
  {
    title: 'Mealplans',
    path: 'meal-plan',
    component: MealPlanListComponent,
    canActivate: [authGuard]
  },
  // {
  //   title: 'Nutritions',
  //   path: 'nutritions',
  //   component: NutritionsComponent,
  //   canActivate: [authGuard]
  // },
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
