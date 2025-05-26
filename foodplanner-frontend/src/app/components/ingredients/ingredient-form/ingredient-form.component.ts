import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef, MatDialogTitle
} from '@angular/material/dialog';
import {IngredientService} from '../../../services/ingredient.service';
import {IngredientCategory} from '../../../models/ingredient-category.model';
import {IngredientCategoryService} from '../../../services/ingredient-category.service';
import {Ingredient} from '../../../models/ingredient.model';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-ingredient-form',
  imports: [
    MatDialogContent,
    MatLabel,
    MatFormField,
    MatSelect,
    MatOption,
    ReactiveFormsModule,
    MatDialogActions,
    MatInput,
    MatButton,
    MatDialogClose,
    MatDialogTitle
  ],
  templateUrl: './ingredient-form.component.html'
})
export class IngredientFormComponent {
  form: FormGroup;
  categories: IngredientCategory[] = [];

  constructor(
    private fb: FormBuilder,
    private ingredientService: IngredientService,
    private categoryService: IngredientCategoryService,
    private dialogRef: MatDialogRef<IngredientFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Ingredient
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      category: ['', Validators.required]
    });

    if (data?.id) {
      this.form.patchValue(data);
    }

    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getAll().subscribe(
      data => this.categories = data
    );
  }

  save(): void {
    if (this.form.valid) {
      const ingredient = {...this.data, ...this.form.value};

      if (ingredient.id) {
        this.ingredientService.update(ingredient.id, ingredient).subscribe(() => {
          this.dialogRef.close(true);
        });
      } else {
        this.ingredientService.create(ingredient).subscribe(() => {
          this.dialogRef.close(true);
        });
      }
    }
  }
}
