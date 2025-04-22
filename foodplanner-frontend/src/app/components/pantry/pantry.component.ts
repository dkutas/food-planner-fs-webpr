import {Component, OnInit} from '@angular/core';
import {GridComponent} from './grid/grid.component';

@Component({
  selector: 'app-pantry',
  templateUrl: './pantry.component.html',
  imports: [
    GridComponent
  ],
  styleUrls: ['./pantry.component.less']
})
export class PantryComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
