import {Component, OnInit} from '@angular/core';
import {PlannerComponent} from './planner/planner.component';
import {MealPlan} from '../../models/meal-plan.model';
import {MealPlanService} from '../../services/meal-plan.service';

@Component({
  selector: 'app-mealplan',
  templateUrl: './mealplan.component.html',
  imports: [
    PlannerComponent
  ],
  styleUrls: ['./mealplan.component.less']
})
export class MealplanComponent implements OnInit {

  mealPlans: MealPlan[] = [];

  constructor(
    private mealPlanService: MealPlanService,
  ) {
  }

  ngOnInit(): void {
    this.loadMealplans();
  }

  loadMealplans() {
    this.mealPlanService
      .getAll().subscribe((res: MealPlan[]) => this.mealPlans = res);
  }


}
