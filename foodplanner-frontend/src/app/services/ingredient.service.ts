import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Ingredient} from '../models/ingredient.model';
import {v4 as uuidv4} from 'uuid';

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

  getById(id: string): Observable<Ingredient> {
    return this.http.get<Ingredient>(`${this.apiUrl}/${id}`);
  }

  create(ingredient: Ingredient): Observable<Ingredient> {
    return this.http.post<Ingredient>(this.apiUrl, {...ingredient, id: uuidv4()});
  }

  update(id: string, ingredient: Ingredient): Observable<Ingredient> {
    return this.http.patch<Ingredient>(`${this.apiUrl}/${id}`, ingredient);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
