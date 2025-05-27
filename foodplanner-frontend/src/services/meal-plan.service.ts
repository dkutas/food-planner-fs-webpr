import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MealPlan, MealPlanInput} from '../models/meal-plan.model';
import {v4 as uuidv4} from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class MealPlanService {
  private apiUrl = 'api/mealplan';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<MealPlan[]> {
    return this.http.get<MealPlan[]>(this.apiUrl);
  }

  getById(id: string): Observable<MealPlan> {
    return this.http.get<MealPlan>(`${this.apiUrl}/${id}`);
  }

  create(mealPlan: MealPlanInput): Observable<MealPlan> {
    return this.http.post<MealPlan>(this.apiUrl, {...mealPlan, id: uuidv4()});
  }

  update(id: string, mealPlan: MealPlanInput): Observable<MealPlan> {
    return this.http.patch<MealPlan>(`${this.apiUrl}/${id}`, mealPlan);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
