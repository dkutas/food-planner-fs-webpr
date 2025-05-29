import {Component, OnInit} from '@angular/core';
import {MealPlan} from '../../models/meal-plan.model';
import {MealPlanService} from '../../services/meal-plan.service';
import {MatDialog} from '@angular/material/dialog';
import {MealPlanFormComponent} from './meal-plan-form/meal-plan-form.component';
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef,
  MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {DatePipe} from '@angular/common';
import {MatTabHeader} from '@angular/material/tabs';

@Component({
  selector: 'app-meal-plan-list',
  templateUrl: './mealplan.component.html',
  imports: [
    MatTable,
    MatHeaderCell,
    MatCell,
    MatHeaderRow,
    MatRow,
    MatIcon,
    MatIconButton,
    MatColumnDef,
    MatButton,
    MatHeaderCellDef,
    MatCellDef,
    DatePipe,
    MatHeaderRowDef,
    MatRowDef,
    MatTabHeader
  ],
  styleUrls: ['./mealplan.component.less']
})
export class MealPlanListComponent implements OnInit {
  mealPlans: MealPlan[] = [];
  displayedColumns: string[] = ['recipe', 'startDate', 'endDate', 'actions'];

  constructor(
    private mealPlanService: MealPlanService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadMealPlans();
  }

  loadMealPlans(): void {
    this.mealPlanService.getAll().subscribe(
      data => this.mealPlans = data
    );
  }

  openForm(mealPlan?: MealPlan): void {
    const dialogRef = this.dialog.open(MealPlanFormComponent, {
      width: '600px',
      data: mealPlan || {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadMealPlans();
    });
  }

  delete(id: string): void {
    if (confirm('Are you sure you want to delete this meal plan?')) {
      this.mealPlanService.delete(id).subscribe(() => {
        this.loadMealPlans();
      });
    }
  }
}
