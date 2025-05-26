import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MealPlanFormComponent} from '../meal-plan-form/meal-plan-form.component';
import {MealPlan} from '../../../models/meal-plan.model';
import {MealPlanService} from '../../../services/meal-plan.service';
import {MatIconButton} from '@angular/material/button';
import {MatCard, MatCardActions, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {RouterLink} from '@angular/router';
import {DatePipe, NgForOf} from '@angular/common';
import {MatIcon} from '@angular/material/icon';
import {map} from 'rxjs';

@Component({
  selector: 'app-meal-planner-calendar',
  templateUrl: './meal-planner-calendar.component.html',
  imports: [
    MatIcon,
    MatIconButton,
    MatCard,
    MatCardTitle,
    MatCardHeader,
    MatCardActions,
    RouterLink,
    NgForOf,
    DatePipe
  ],
  styleUrls: ['./meal-planner-calendar.component.less']
})
export class MealPlannerCalendarComponent implements OnInit {
  mealPlans: MealPlan[] = [];
  selectedDate: Date = new Date();
  weekDays: Date[] = [];

  constructor(
    private mealPlanService: MealPlanService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.calculateWeekDays();
    this.loadMealPlans();
  }

  calculateWeekDays(): void {
    this.weekDays = [];
    const firstDay = new Date(this.selectedDate);
    firstDay.setDate(firstDay.getDate() - firstDay.getDay()); // Start from Sunday

    for (let i = 0; i < 7; i++) {
      const day = new Date(firstDay);
      day.setDate(firstDay.getDate() + i);
      this.weekDays.push(day);
    }
  }

  loadMealPlans(): void {
    const startDate = this.weekDays[0];
    const endDate = this.weekDays[6];

    this.mealPlanService.getAll().pipe(
      map(plans => plans.filter(plan => {
        const planDate = new Date(plan.startDate);
        return planDate >= startDate && planDate <= endDate;
      }))
    ).subscribe(
      plans => this.mealPlans = plans
    );
  }

  getMealPlansForDay(date: Date): MealPlan[] {
    return this.mealPlans.filter(plan => {
      const planDate = new Date(plan.startDate);
      return planDate.toDateString() === date.toDateString();
    });
  }

  previousWeek(): void {
    this.selectedDate.setDate(this.selectedDate.getDate() - 7);
    this.calculateWeekDays();
    this.loadMealPlans();
  }

  nextWeek(): void {
    this.selectedDate.setDate(this.selectedDate.getDate() + 7);
    this.calculateWeekDays();
    this.loadMealPlans();
  }

  addMealPlan(date: Date): void {
    const dialogRef = this.dialog.open(MealPlanFormComponent, {
      width: '600px',
      data: {startDate: date}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadMealPlans();
    });
  }

  deleteMealPlan(id: string): void {
    if (confirm('Are you sure you want to delete this meal plan?')) {
      this.mealPlanService.delete(id).subscribe(() => {
        this.loadMealPlans();
      });
    }
  }
}
