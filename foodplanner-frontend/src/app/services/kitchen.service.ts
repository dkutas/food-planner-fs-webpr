import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Kitchen} from '../models/kitchen.model';
import {v4 as uuidv4} from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class KitchenService {
  private apiUrl = 'api/kitchen';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Kitchen[]> {
    return this.http.get<Kitchen[]>(this.apiUrl);
  }

  getById(id: string): Observable<Kitchen> {
    return this.http.get<Kitchen>(`${this.apiUrl}/${id}`);
  }

  create(kitchen: Kitchen): Observable<Kitchen> {
    return this.http.post<Kitchen>(this.apiUrl, {...kitchen, id: uuidv4()});
  }

  update(id: string, kitchen: Kitchen): Observable<Kitchen> {
    return this.http.patch<Kitchen>(`${this.apiUrl}/${id}`, kitchen);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
