import {Injectable} from '@angular/core';
import {delay, of, tap} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient,
              private authService: AuthService) {
  }

  login() {
    return of({
      token: 'Ugly little token',
      username: 'Ugly Ubul'
    }).pipe(
      delay(1000),
      tap((response) => {
        this.authService.saveSession(response);
      })
    );
  }

}


