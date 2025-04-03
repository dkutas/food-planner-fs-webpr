import {Component, OnInit} from '@angular/core';
import {GridComponent} from './grid/grid.component';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  imports: [
    GridComponent
  ],
  styleUrls: ['./recipes.component.less']
})
export class RecipesComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
