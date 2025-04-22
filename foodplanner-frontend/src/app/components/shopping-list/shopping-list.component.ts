import {Component, OnInit} from '@angular/core';
import {GridComponent} from './grid/grid.component';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.less'],
  imports: [GridComponent, GridComponent]
})
export class ShoppingListComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
