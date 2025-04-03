import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {NzGridModule} from 'ng-zorro-antd/grid';
import {NzMarks, NzSliderModule} from 'ng-zorro-antd/slider';

@Component({
  selector: 'shopping-grid',
  imports: [FormsModule, NzGridModule, NzSliderModule],
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.less']
})
export class GridComponent {
  hGutter = 16;
  vGutter = 16;
  count = 3;
  array = new Array(this.count);


}
