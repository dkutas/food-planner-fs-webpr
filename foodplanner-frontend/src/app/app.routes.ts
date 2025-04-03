import {Routes} from '@angular/router';
import {MealplanComponent} from './components/mealplan/mealplan.component';
import {NutritionsComponent} from './components/nutritions/nutritions.component';
import {PantryComponent} from './components/pantry/pantry.component';
import {RecipesComponent} from './components/recipes/recipes.component';
import {ShoppingListComponent} from './components/shopping-list/shopping-list.component';
import {AppComponent} from './app.component';
import {RegisterComponent} from './components/auth/register/register.component';
import {LoginComponent} from './components/auth/login/login.component';
import {privateGuard} from './guards/private.guard';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    title: 'Mealplan',
    path: 'meal-plan',
    component: MealplanComponent,
    canActivate: [privateGuard]
  },
  {
    title: 'Nutritions',
    path: 'nutritions',
    component: NutritionsComponent,
    canActivate: [privateGuard]
  },
  {
    title: 'Pantry',
    path: 'pantry',
    component: PantryComponent,
    canActivate: [privateGuard]
  },
  {
    title: 'Recipes',
    path: 'recipes',
    component: RecipesComponent
  },
  {
    title: 'Shopping List',
    path: 'shopping-list',
    component: ShoppingListComponent,
    canActivate: [privateGuard]
  }, {
    title: 'Log in',
    path: 'login',
    component: LoginComponent,
  }, {
    title: 'Register',
    path: 'register',
    component: RegisterComponent,
  },
];
