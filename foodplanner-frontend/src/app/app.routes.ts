import {Routes} from '@angular/router';
import {MealplanComponent} from './components/mealplan/mealplan.component';
import {NutritionsComponent} from './components/nutritions/nutritions.component';
import {PantryComponent} from './components/pantry/pantry.component';
import {RecipesComponent} from './components/recipes/recipes.component';
import {ShoppingListComponent} from './components/shopping-list/shopping-list.component';

export const routes: Routes = [
  {title: 'Mealplan', path: 'meal-plan', component: MealplanComponent},
  {title: 'Nutritions', path: 'nutritions', component: NutritionsComponent},
  {title: 'Pantry', path: 'pantry', component: PantryComponent},
  {title: 'Recipes', path: 'recipes', component: RecipesComponent},
  {
    title: 'Shopping List',
    path: 'shopping-list',
    component: ShoppingListComponent,
  },
];
