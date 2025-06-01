// meal-plan-scheduler.component.ts
import {Component, OnInit} from '@angular/core';
import {CalendarOptions, EventInput} from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import {MealPlan, MealPlanInput} from '../../../models/meal-plan.model';
import {MealPlanService} from '../../../services/meal-plan.service';
import {MatDialog} from '@angular/material/dialog';
import {MealPlanFormComponent} from '../meal-plan-form/meal-plan-form.component';
import {FullCalendarModule} from '@fullcalendar/angular';

@Component({
    selector: 'app-meal-plan-scheduler',
    templateUrl: './meal-plan-scheduler.component.html',
    imports: [
        FullCalendarModule
    ],
    styleUrls: ['./meal-plan-scheduler.component.less']
})
export class MealPlanSchedulerComponent implements OnInit {
    mealPlans: MealPlan[] = [];
    calendarOptions: CalendarOptions = {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,dayGridWeek'
        },
        editable: true,
        selectable: true,
        events: [],
        eventClassNames: ['event-meal-plan'],
        dateClick: this.handleDateClick.bind(this),
        eventClick: this.handleEventClick.bind(this),
        eventDrop: this.handleEventChange.bind(this),
        eventResize: this.handleEventChange.bind(this)
    };

    constructor(
        private mealPlanService: MealPlanService,
        private dialog: MatDialog
    ) {
    }

    ngOnInit(): void {
        this.loadMealPlans();
    }

    loadMealPlans(): void {
        this.mealPlanService.getAll().subscribe(mealPlans => {
            this.mealPlans = mealPlans;
            this.calendarOptions.events = mealPlans.map(mealPlan => ({
                id: mealPlan.id,
                title: mealPlan.recipe.name,
                start: mealPlan.startDate,
                end: mealPlan.endDate,
                extendedProps: {
                    description: mealPlan.recipe.description,
                    mealPlan: mealPlan
                },
                allDay: true
            }));
        });
    }

    handleDateClick(arg: any): void {
        this.openForm({
            id: 'new',
            startDate: arg.dateStr,
            endDate: arg.dateStr
        });
    }

    handleEventClick(arg: any): void {
        this.openForm(arg.event.extendedProps.mealPlan);
    }

    handleEventChange(arg: any): void {
        const mealInput: MealPlanInput = {
            recipeId: arg.event.extendedProps.mealPlan.recipe.id,
            startDate: new Date(arg.event.startStr).toISOString(),
            endDate: new Date(arg.event.endStr).toISOString()
        };

        this.mealPlanService.update(arg.event.id, mealInput).subscribe(() => {
            const mealPlanToUpdate = this.mealPlans.find(mealPlan => mealPlan.id === arg.event.id);
            if (mealPlanToUpdate) {
                mealPlanToUpdate.startDate = new Date(mealInput.startDate).toISOString();
                mealPlanToUpdate.endDate = new Date(mealInput.endDate).toISOString();
            }
        });
    }

    openForm(mealPlan?: Partial<MealPlan>): void {
        const dialogRef = this.dialog.open(MealPlanFormComponent, {
            width: '600px',
            data: mealPlan || {}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) this.loadMealPlans();
        });
    }
}
