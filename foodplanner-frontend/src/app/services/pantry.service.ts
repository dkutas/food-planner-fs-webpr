// pantry.service.ts
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Pantry} from '../models/pantry-model';

@Injectable({
  providedIn: 'root'
})
export class PantryService {
  private apiUrl = '/api/pantry';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Pantry[]> {
    return this.http.get<Pantry[]>(this.apiUrl);
  }

  getById(id: number): Observable<Pantry> {
    return this.http.get<Pantry>(`${this.apiUrl}/${id}`);
  }

  create(pantry: Pantry): Observable<Pantry> {
    return this.http.post<Pantry>(this.apiUrl, pantry);
  }

  update(id: number, pantry: Pantry): Observable<Pantry> {
    return this.http.put<Pantry>(`${this.apiUrl}/${id}`, pantry);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
