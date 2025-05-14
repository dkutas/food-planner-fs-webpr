// kitchen.service.ts
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Kitchen} from '../models/kitchen.model';

@Injectable({
  providedIn: 'root'
})
export class KitchenService {
  private apiUrl = '/api/kitchen';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Kitchen[]> {
    return this.http.get<Kitchen[]>(this.apiUrl);
  }

  getById(id: number): Observable<Kitchen> {
    return this.http.get<Kitchen>(`${this.apiUrl}/${id}`);
  }

  create(kitchen: Kitchen): Observable<Kitchen> {
    return this.http.post<Kitchen>(this.apiUrl, kitchen);
  }

  update(id: number, kitchen: Kitchen): Observable<Kitchen> {
    return this.http.put<Kitchen>(`${this.apiUrl}/${id}`, kitchen);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

