import {Component} from '@angular/core';
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
