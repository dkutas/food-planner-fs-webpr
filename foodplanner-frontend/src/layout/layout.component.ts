// layout.component.ts
import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {NgForOf, NgOptimizedImage} from '@angular/common';
import {RouterService} from '../services/router.service';
import {KeycloakService} from '../services/keycloak/keycloak.service';
import {routes} from '../app.routes';
import {MatToolbar} from "@angular/material/toolbar";
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";
import {MatListItem, MatNavList} from "@angular/material/list";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.less'],
  imports: [
    MatToolbar,
    MatSidenavContainer,
    MatSidenav,
    MatSidenavContent,
    MatListItem,
    MatButton,
    RouterOutlet,
    NgOptimizedImage,
    RouterLinkActive,
    RouterLink,
    NgForOf,
    MatNavList
  ],
  standalone: true
})
export class LayoutComponent {
  menuRoutes = routes.filter(route =>
    route.path !== '' &&
    route.path !== 'login' &&
    route.path !== 'register' &&
    route.title &&
    route.data?.['renderLink'] !== false
  );

  constructor(
    public routerService: RouterService,
    public keycloakService: KeycloakService
  ) {
  }

  getRouteFn(path: string): () => void {
    return () => this.routerService.routeToPath(path);
  }
}
