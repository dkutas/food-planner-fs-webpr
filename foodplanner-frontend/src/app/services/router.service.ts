import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router: Router) {
  }

  routeToPath(path: string) {
    this.router.navigateByUrl(path);
  }
}
