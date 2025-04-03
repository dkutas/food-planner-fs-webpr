import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {NzGridModule} from 'ng-zorro-antd/grid';
import {NzMarks, NzSliderModule} from 'ng-zorro-antd/slider';
import {NzCardComponent} from 'ng-zorro-antd/card';

@Component({
  selector: 'recipe-grid',
  imports: [FormsModule, NzGridModule, NzSliderModule, NzCardComponent],
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.less']
})
export class GridComponent {
  hGutter = 16;
  vGutter = 16;
  count = 6;
  cols = 3;
  colArr = new Array<number>(this.count).map((_, i) => i);


}
