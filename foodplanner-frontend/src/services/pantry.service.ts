import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Pantry, PantryInput} from '../models/pantry-model';
import {v4 as uuidv4} from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class PantryService {
  private apiUrl = 'api/pantry';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Pantry[]> {
    return this.http.get<Pantry[]>(this.apiUrl);
  }

  getById(id: string): Observable<Pantry> {
    return this.http.get<Pantry>(`${this.apiUrl}/${id}`);
  }

  create(pantry: PantryInput): Observable<Pantry> {
    return this.http.post<Pantry>(this.apiUrl, {...pantry, id: uuidv4()});
  }

  update(id: string, pantry: PantryInput): Observable<Pantry> {
    return this.http.patch<Pantry>(`${this.apiUrl}/${id}`, pantry);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
