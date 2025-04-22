import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router: Router) {
  }

  routeToMealPlan() {
    this.router.navigateByUrl('/meal-plan');
  }

  routeToLogin() {
    this.router.navigateByUrl('/login');
  }

  routeToRegister() {
    this.router.navigateByUrl('/register');
  }

  routeToPantry() {
    this.router.navigateByUrl('/pantry');
  }

  routeToRecipe() {
    this.router.navigateByUrl('/recipes');
  }

  routeToShoppingList() {
    this.router.navigateByUrl('/shopping-list');
  }

  routeToNutrition() {
    this.router.navigateByUrl('/nutritions');
  }
}
