import {Component} from '@angular/core';

import {NzGridModule} from 'ng-zorro-antd/grid';
import {FormsModule} from '@angular/forms';
import {NzCardComponent} from 'ng-zorro-antd/card';
import {PantryCardComponent} from './pantry-card/pantry-card.component';

@Component({
  selector: 'pantry-grid',
  imports: [FormsModule, NzGridModule, NzCardComponent, PantryCardComponent],
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.less'],
})
export class GridComponent {
  hGutter = 16;
  vGutter = 16;
  colors = ["#ffd4e5", "#d4ffea", "#eecbff", "#feffa3", "#dbdcff", "#b8dbd3", "#d4ffea", "#ffd4e5", "#d4ffea", "#eecbff", "#feffa3", "#dbdcff", "#b8dbd3"];
  categories: string[] = ["Fruits", "Vegetables", "Dairy", "Meat", "Grains"];
  count = 3;
  cols = 3;


  protected readonly Math = Math;
}
