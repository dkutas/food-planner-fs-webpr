import {CanActivateFn} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {RouterService} from '../services/router.service';

export const privateGuard: CanActivateFn = (route, state) => {
  const isLoggedIn = inject(AuthService).isLoggedIn;
  // console.log(`privateGuard: isLoggedIn: ${isLoggedIn}`);
  if (!isLoggedIn) {
    inject(RouterService).routeToLogin();
  }
  return isLoggedIn;
};
