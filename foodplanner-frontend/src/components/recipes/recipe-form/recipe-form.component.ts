import {Component, Inject} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {Kitchen} from '../../../models/kitchen.model';
import {RecipeService} from '../../../services/recipe.service';
import {KitchenService} from '../../../services/kitchen.service';
import {Recipe, RecipeInput} from '../../../models/recipe.model';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatButton} from '@angular/material/button';
import {NgForOf} from '@angular/common';
import {Ingredient} from '../../../models/ingredient.model';
import {IngredientService} from '../../../services/ingredient.service';


@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  imports: [
    MatDialogContent,
    MatFormField,
    ReactiveFormsModule,
    MatSelect,
    MatDialogTitle,
    MatInput,
    MatOption,
    MatDialogActions,
    MatButton,
    MatDialogClose,
    MatLabel,
    NgForOf
  ],
  styleUrls: ['./recipe-form.component.less']
})
export class RecipeFormComponent {
  form: FormGroup;
  kitchens: Kitchen[] = [];
  ingredients: Ingredient[] = [];

  constructor(
    private fb: FormBuilder,
    private recipeService: RecipeService,
    private kitchenService: KitchenService,
    private dialogRef: MatDialogRef<RecipeFormComponent>,
    private ingredientService: IngredientService,
    @Inject(MAT_DIALOG_DATA) public data: Recipe
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      kitchen: [{}, Validators.required],
      ingredients: [[], Validators.required],
    });

    if (data?.id) {
      this.form.patchValue(data);
    }

    this.loadKitchens();
    this.loadIngredients();
  }

  loadKitchens(): void {
    this.kitchenService.getAll().subscribe(
      data => this.kitchens = data
    );
  }

  loadIngredients(): void {
    this.ingredientService.getAll().subscribe(
      data => this.ingredients = data
    );
  }

  save(): void {
    if (this.form.valid) {
      console.log(this.form.value);
      // console.log(this.data);
      const recipe: RecipeInput = {
        id: this.form.value.id,
        name: this.form.value.name,
        description: this.form.value.description,
        ingredientIds: this.form.value.ingredients.map((i: Ingredient) => i.id),
        kitchenId: this.form.value.kitchen.id
      };

      if (recipe.id) {
        this.recipeService.update(recipe.id, recipe).subscribe(() => {
          this.dialogRef.close(true);
        });
      } else {
        this.recipeService.create(recipe).subscribe(() => {
          this.dialogRef.close(true);
        });
      }
    }
  }
}
