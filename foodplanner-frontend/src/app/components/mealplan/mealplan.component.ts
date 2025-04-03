import {Component, OnInit} from '@angular/core';
import {PlannerComponent} from './planner/planner.component';

@Component({
  selector: 'app-mealplan',
  templateUrl: './mealplan.component.html',
  imports: [
    PlannerComponent
  ],
  styleUrls: ['./mealplan.component.less']
})
export class MealplanComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
