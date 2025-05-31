import {Component, Inject} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {NgForOf} from '@angular/common';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatOption, MatSelect} from '@angular/material/select';
import {Recipe} from '../../../models/recipe.model';
import {MealPlanService} from '../../../services/meal-plan.service';
import {RecipeService} from '../../../services/recipe.service';
import {MealPlan, MealPlanInput} from '../../../models/meal-plan.model';
import {
  MatDatepicker,
  MatDatepickerInput, MatDatepickerModule,
  MatDatepickerToggle,
  MatDateRangeInput,
  MatDateRangePicker,
} from '@angular/material/datepicker';
import {MatButton} from '@angular/material/button';
import {MatNativeDateModule} from '@angular/material/core';

@Component({
  selector: 'app-meal-plan-form',
  templateUrl: './meal-plan-form.component.html',
  imports: [
    MatDialogTitle,
    MatDialogContent,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatSelect,
    MatOption,
    NgForOf,
    MatFormField,
    MatFormFieldModule,
    MatDatepickerToggle,
    MatDialogActions,
    MatButton,
    MatDialogClose,
    MatDateRangeInput,
    MatDateRangePicker,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  styleUrls: ['./meal-plan-form.component.less']
})
export class MealPlanFormComponent {
  form: FormGroup;
  recipes: Recipe[] = [];
  dateRange = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  constructor(
    private fb: FormBuilder,
    private mealPlanService: MealPlanService,
    private recipeService: RecipeService,
    private dialogRef: MatDialogRef<MealPlanFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: MealPlan
  ) {
    this.form = this.fb.group({
      recipe: [this.data?.recipe?.name || '', Validators.required],
      dateRange: [this.dateRange, Validators.required],
    });

    if (data?.id) {
      this.dateRange.patchValue({
        start: new Date(data.startDate),
        end: new Date(data.endDate)
      });
      this.form.patchValue({
        recipe: data.recipe
      });
    }

    this.loadRecipes();
  }

  compareRecipes(option: Recipe, value: Recipe): boolean {
    return option && value ? option.id === value.id : option === value;
  }


  loadRecipes(): void {
    this.recipeService.getAll().subscribe(
      data => this.recipes = data
    );
  }

  save(): void {
    if (this.form.valid) {
      const mealPlan: MealPlanInput = {
        recipeId: this.form.value.recipe.id,
        startDate: this.dateRange.value.start?.toISOString() || new Date().toISOString(),
        endDate: this.dateRange.value.end?.toISOString() || new Date().toISOString(),
      };

      if (this.data.id && this.data.id !== 'new') {
        this.mealPlanService.update(this.data.id, mealPlan).subscribe(() => {
          this.dialogRef.close(true);
        });
      } else {
        this.mealPlanService.create(mealPlan).subscribe(() => {
          this.dialogRef.close(true);
        });
      }
    }
  }
}
