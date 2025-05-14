// meal-plan.service.ts
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MealPlan} from '../models/meal-plan.model';

@Injectable({
  providedIn: 'root'
})
export class MealPlanService {
  private apiUrl = '/api/mealplan';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<MealPlan[]> {
    return this.http.get<MealPlan[]>(this.apiUrl);
  }

  getById(id: number): Observable<MealPlan> {
    return this.http.get<MealPlan>(`${this.apiUrl}/${id}`);
  }

  create(mealPlan: MealPlan): Observable<MealPlan> {
    return this.http.post<MealPlan>(this.apiUrl, mealPlan);
  }

  update(id: number, mealPlan: MealPlan): Observable<MealPlan> {
    return this.http.put<MealPlan>(`${this.apiUrl}/${id}`, mealPlan);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
