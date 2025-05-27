import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Kitchen} from '../models/kitchen.model';

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
}
