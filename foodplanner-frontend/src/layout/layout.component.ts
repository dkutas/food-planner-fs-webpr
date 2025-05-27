import {Component} from '@angular/core';
import {NzBreadCrumbModule} from 'ng-zorro-antd/breadcrumb';
import {NzIconModule} from 'ng-zorro-antd/icon';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {NgForOf, NgOptimizedImage} from '@angular/common';
import {RouterService} from '../services/router.service';
import {KeycloakService} from '../services/keycloak/keycloak.service';
import {routes} from '../app.routes';

@Component({
  selector: 'app-layout',
  imports: [NzBreadCrumbModule, NzIconModule, NzMenuModule, NzLayoutModule, RouterOutlet, NgOptimizedImage, RouterLinkActive, RouterLink, NgForOf],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.less']
})
export class LayoutComponent {
  menuRoutes = routes.filter(route =>
    route.path !== '' &&
    route.path !== 'login' &&
    route.path !== 'register' &&
    route.title
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
