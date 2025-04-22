import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NzBreadCrumbModule} from 'ng-zorro-antd/breadcrumb';
import {NzIconModule} from 'ng-zorro-antd/icon';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {LayoutComponent} from './layout/layout.component';

@Component({
  selector: 'app-root',
  imports: [
    LayoutComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less'],
})
export class AppComponent {
  title = 'foodplanner-frontend';
}
