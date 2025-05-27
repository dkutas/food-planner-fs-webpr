import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Recipe, RecipeInput} from '../models/recipe.model';
import {v4 as uuidv4} from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  private apiUrl = 'api/recipe';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.apiUrl);
  }

  getById(id: string): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.apiUrl}/${id}`);
  }

  create(recipe: RecipeInput): Observable<RecipeInput> {
    return this.http.post<RecipeInput>(this.apiUrl, {...recipe, id: uuidv4()});
  }

  update(id: string, recipe: RecipeInput): Observable<RecipeInput> {
    return this.http.patch<RecipeInput>(`${this.apiUrl}/${id}`, recipe);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
