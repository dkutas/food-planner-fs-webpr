import {ApplicationConfig, inject, provideAppInitializer, provideZoneChangeDetection} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {KeycloakService} from './services/keycloak/keycloak.service';

export function initializeKeycloak(keycloakService: KeycloakService) {
  return keycloakService.initializeKeycloak();
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAppInitializer(() => initializeKeycloak(inject(KeycloakService)))
  ],
};
