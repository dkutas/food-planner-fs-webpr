import {Component, Inject} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {AsyncPipe, NgForOf} from '@angular/common';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatOption} from '@angular/material/select';
import {Recipe} from '../../../models/recipe.model';
import {MealPlanService} from '../../../services/meal-plan.service';
import {RecipeService} from '../../../services/recipe.service';
import {MealPlan, MealPlanInput} from '../../../models/meal-plan.model';
import {
  MatDatepickerModule,
  MatDatepickerToggle,
  MatDateRangeInput,
  MatDateRangePicker,
} from '@angular/material/datepicker';
import {MatButton} from '@angular/material/button';
import {MatNativeDateModule} from '@angular/material/core';
import {ShoppingListService} from '../../../services/shopping-list.service';
import {IngredientService} from '../../../services/ingredient.service';
import {add} from 'date-fns';
import {map, Observable, startWith} from 'rxjs';
import {MatAutocomplete, MatAutocompleteSelectedEvent, MatAutocompleteTrigger} from '@angular/material/autocomplete';

@Component({
  selector: 'app-meal-plan-form',
  templateUrl: './meal-plan-form.component.html',
  imports: [
    MatDialogTitle,
    MatDialogContent,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
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
    MatAutocompleteTrigger,
    MatAutocomplete,
    MatInput,
    AsyncPipe,
  ],
  styleUrls: ['./meal-plan-form.component.less']
})
export class MealPlanFormComponent {
  form: FormGroup;
  recipes: Recipe[] = [];
  dateRange: FormGroup;
  recipeControl = new FormControl('');
  filteredRecipes: Observable<Recipe[]>;

  constructor(
    private fb: FormBuilder,
    private mealPlanService: MealPlanService,
    private recipeService: RecipeService,
    private shoppingListService: ShoppingListService,
    private ingredientService: IngredientService,
    private dialogRef: MatDialogRef<MealPlanFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: MealPlan
  ) {
    this.dateRange = this.fb.group({
      start: [data?.startDate || null, Validators.required],
      end: [data?.endDate || null, Validators.required]
    });

    this.form = this.fb.group({
      recipe: [this.data?.recipe?.name || '', Validators.required],
      dateRange: [this.dateRange, Validators.required],
    });

    this.loadRecipes();

    this.filteredRecipes = this.recipeControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        return this._filter(value || '');
      })
    );

    if (data?.id) {
      this.dateRange.patchValue({
        start: new Date(data.startDate),
        end: new Date(data.endDate)
      });
      this.form.patchValue({
        recipe: data.recipe
      });
    }

  }

  private _filter(value: string | Recipe): Recipe[] {
    if (typeof value === 'object') {
      return this.recipes.filter(recipe =>
        recipe.name.toLowerCase().includes(value.name.toLowerCase())
      );
    } else if (!value) {
      return this.recipes;
    } else {
      const filterValue = value.toLowerCase();
      return this.recipes.filter(recipe =>
        recipe.name.toLowerCase().includes(filterValue)
      )
    }
  }

  displayFn(recipe: Recipe): string {
    return recipe?.name || '';
  }

  loadRecipes(): void {
    this.recipeService.getAll().subscribe(recipes => {
      this.recipes = recipes;
    });
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.form.patchValue({recipe: event.option.value});
  }

  save(): void {
    if (this.form.valid) {
      const mealPlan: MealPlanInput = {
        recipeId: this.form.value.recipe.id,
        startDate: this.dateRange.value.start?.toISOString() || new Date().toISOString(),
        endDate: this.dateRange.value.end?.toISOString() || new Date().toISOString(),
      };

      const handleNewIngredients = () => {
        const currentDate = new Date();
        this.ingredientService.getMissingForMealPlan({
          startDate: currentDate.toISOString(),
          endDate: add(currentDate, {months: 1}).toISOString()
        }).subscribe((missingIngredients) => {
          let existingIngredients: string[] = [];
          this.shoppingListService.getAll().subscribe(shoppingList => {
            existingIngredients = shoppingList.map(item => item.ingredient.id);
          });
          missingIngredients.forEach(ingredient => {
            if (!existingIngredients.includes(ingredient.ingredientId)) {
              this.shoppingListService.create({ingredientId: ingredient.ingredientId}).subscribe()
            }
          })
        })
        this.dialogRef.close(true);
      }

      if (this.data.id && this.data.id !== 'new') {
        this.mealPlanService.update(this.data.id, mealPlan).subscribe(handleNewIngredients);
      } else {
        this.mealPlanService.create(mealPlan).subscribe(handleNewIngredients);
      }
    }
  }
}
