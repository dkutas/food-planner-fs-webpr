import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {NgForOf} from '@angular/common';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {Recipe} from '../../../models/recipe.model';
import {MealPlanService} from '../../../services/meal-plan.service';
import {RecipeService} from '../../../services/recipe.service';
import {MealPlan, MealPlanInput} from '../../../models/meal-plan.model';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {MatButton} from '@angular/material/button';

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
    MatInput,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatDatepicker,
    MatDialogActions,
    MatButton,
    MatDialogClose
  ],
  styleUrls: ['./meal-plan-form.component.less']
})
export class MealPlanFormComponent {
  form: FormGroup;
  recipes: Recipe[] = [];

  constructor(
    private fb: FormBuilder,
    private mealPlanService: MealPlanService,
    private recipeService: RecipeService,
    private dialogRef: MatDialogRef<MealPlanFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: MealPlan
  ) {
    this.form = this.fb.group({
      recipe: ['', Validators.required],
      startDate: [data?.startDate || '', Validators.required],
      endDate: [data?.endDate || '', Validators.required]
    });

    if (data?.id) {
      this.form.patchValue(data);
    }

    this.loadRecipes();
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
        startDate: new Date(this.form.value.startDate).toISOString(),
        endDate: new Date(this.form.value.endDate).toISOString(),
      };
      console.log(mealPlan);

      if (this.data.id) {
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
