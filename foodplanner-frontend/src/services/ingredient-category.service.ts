import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IngredientCategory} from '../models/ingredient-category.model';

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
}
