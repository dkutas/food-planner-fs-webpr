import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Ingredient, MissingIngredientByMeal} from '../models/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  private apiUrl = 'api/ingredient';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(this.apiUrl);
  }

  getMissingForShoppingList(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(`${this.apiUrl}/missing/shoppinglist`);
  }

  getMissingForPanty(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(`${this.apiUrl}/missing/pantry`);
  }

  getMissingForMealPlan(dates: { startDate: string, endDate: string }): Observable<MissingIngredientByMeal[]> {
    return this.http.get<MissingIngredientByMeal[]>(`${this.apiUrl}/missing/mealplan?startDate=${dates.startDate}&endDate=${dates.endDate}`);
  }
}
