import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IngredientCategory} from '../models/ingredient-category.model';
import {v4 as uuidv4} from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class IngredientCategoryService {
  private apiUrl = 'api/ingredient/category';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<IngredientCategory[]> {
    return this.http.get<IngredientCategory[]>(this.apiUrl);
  }

  getById(id: string): Observable<IngredientCategory> {
    return this.http.get<IngredientCategory>(`${this.apiUrl}/${id}`);
  }

  create(category: IngredientCategory): Observable<IngredientCategory> {
    return this.http.post<IngredientCategory>(this.apiUrl, {...category, id: uuidv4()});
  }

  update(id: string, category: IngredientCategory): Observable<IngredientCategory> {
    return this.http.patch<IngredientCategory>(`${this.apiUrl}/${id}`, category);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
