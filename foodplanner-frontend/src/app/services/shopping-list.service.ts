import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ShoppingList} from '../models/shopping-list.model';

@Injectable({
  providedIn: 'root'
})
export class ShoppingListService {
  private apiUrl = 'api/shopping-lists';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<ShoppingList[]> {
    return this.http.get<ShoppingList[]>(this.apiUrl);
  }

  getById(id: string): Observable<ShoppingList> {
    return this.http.get<ShoppingList>(`${this.apiUrl}/${id}`);
  }

  create(item: ShoppingList): Observable<ShoppingList> {
    return this.http.post<ShoppingList>(this.apiUrl, item);
  }

  update(id: string, item: ShoppingList): Observable<ShoppingList> {
    return this.http.put<ShoppingList>(`${this.apiUrl}/${id}`, item);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
