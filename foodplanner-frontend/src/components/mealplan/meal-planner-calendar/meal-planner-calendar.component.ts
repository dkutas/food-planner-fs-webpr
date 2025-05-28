import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MealPlanFormComponent} from '../meal-plan-form/meal-plan-form.component';
import {CdkDrag, CdkDragDrop, CdkDropList, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {MealPlan} from '../../../models/meal-plan.model';
import {MealPlanService} from '../../../services/meal-plan.service';
import {map} from 'rxjs';
import {DatePipe, NgForOf} from '@angular/common';
import {MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {MatCard, MatCardActions, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {RouterLink} from '@angular/router';

interface CalendarDay {
  date: Date;
  isCurrentMonth: boolean;
  mealPlans: MealPlan[];
  id: string;
}

@Component({
  selector: 'app-meal-planner-calendar',
  templateUrl: './meal-planner-calendar.component.html',
  imports: [
    MatIcon,
    DatePipe,
    MatIconButton,
    CdkDropList,
    NgForOf,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardActions,
    RouterLink,
    CdkDrag
  ],
  styleUrls: ['./meal-planner-calendar.component.less']
})
export class MealPlannerCalendarComponent implements OnInit {
  currentDate = new Date();
  calendarDays: CalendarDay[] = [];
  mealPlans: MealPlan[] = [];
  connectedDropLists: string[] = [];
  weekDays = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];

  constructor(
    private mealPlanService: MealPlanService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.generateCalendar();
    this.loadMealPlans();
  }

  generateCalendar(): void {
    this.calendarDays = [];
    const firstDay = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), 1);
    const lastDay = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth() + 1, 0);

    // Add days from previous month
    const firstDayOfWeek = firstDay.getDay();
    for (let i = firstDayOfWeek - 1; i >= 0; i--) {
      const date = new Date(firstDay);
      date.setDate(date.getDate() - i - 1);
      this.calendarDays.push({
        date,
        isCurrentMonth: false,
        mealPlans: [],
        id: `day-${date.getTime()}`
      });
    }

    // Add days of current month
    for (let date = new Date(firstDay); date <= lastDay; date.setDate(date.getDate() + 1)) {
      this.calendarDays.push({
        date: new Date(date),
        isCurrentMonth: true,
        mealPlans: [],
        id: `day-${date.getTime()}`
      });
    }

    // Add days from next month
    const remainingDays = 42 - this.calendarDays.length;
    for (let i = 1; i <= remainingDays; i++) {
      const date = new Date(lastDay);
      date.setDate(date.getDate() + i);
      this.calendarDays.push({
        date,
        isCurrentMonth: false,
        mealPlans: [],
        id: `day-${date.getTime()}`
      });
    }

    this.connectedDropLists = this.calendarDays.map(day => day.id);
  }

  loadMealPlans(): void {
    const startDate = this.calendarDays[0].date;
    const endDate = this.calendarDays[this.calendarDays.length - 1].date;

    this.mealPlanService.getAll().pipe(
      map(plans => plans.filter(plan => {
        const planDate = new Date(plan.startDate);
        return planDate >= startDate && planDate <= endDate;
      }))
    ).subscribe(plans => {
      this.mealPlans = plans;
      this.updateCalendarMealPlans();
    });
  }

  updateCalendarMealPlans(): void {
    this.calendarDays.forEach(day => {
      day.mealPlans = this.mealPlans.filter(plan =>
        new Date(plan.startDate).toDateString() === day.date.toDateString()
      );
    });
  }

  previousMonth(): void {
    this.currentDate = new Date(this.currentDate.setMonth(this.currentDate.getMonth() - 1));
    this.generateCalendar();
    this.loadMealPlans();
  }

  nextMonth(): void {
    this.currentDate = new Date(this.currentDate.setMonth(this.currentDate.getMonth() + 1));
    this.generateCalendar();
    this.loadMealPlans();
  }

  onDrop(event: CdkDragDrop<MealPlan[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      const plan = event.previousContainer.data[event.previousIndex];
      const targetDate = this.calendarDays.find(
        day => day.id === event.container.id
      )?.date;

      if (targetDate) {
        const updatedPlan = {
          ...plan,
          recipeId: plan.recipe.id,
          startDate: targetDate.toISOString()
        };

        this.mealPlanService.update(plan.id, updatedPlan).subscribe(() => {
          transferArrayItem(
            event.previousContainer.data,
            event.container.data,
            event.previousIndex,
            event.currentIndex
          );
        });
      }
    }
  }

  addMealPlan(date: Date): void {
    const dialogRef = this.dialog.open(MealPlanFormComponent, {
      width: '600px',
      data: {startDate: date, endDate: date},
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
