import {ApplicationConfig, inject, provideAppInitializer, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {KeycloakService} from './services/keycloak/keycloak.service';

export function initializeKeycloak(keycloakService: KeycloakService) {
  return keycloakService.initializeKeycloak();
}

import {routes} from './app.routes';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {httpTokenInterceptor} from './interceptors/http-token.interceptor';
import {provideNativeDateAdapter} from '@angular/material/core';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes),
    provideAppInitializer(() => initializeKeycloak(inject(KeycloakService))),
    provideAnimationsAsync(),
    provideHttpClient(withInterceptors([httpTokenInterceptor])),
    provideNativeDateAdapter()
  ],

};
