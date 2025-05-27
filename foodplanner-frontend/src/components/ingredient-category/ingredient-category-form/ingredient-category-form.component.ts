import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {IngredientCategoryService} from '../../../services/ingredient-category.service';
import {IngredientCategory} from '../../../models/ingredient-category.model';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-ingredient-category-form',
  templateUrl: './ingredient-category-form.component.html',
  imports: [
    MatDialogTitle,
    MatDialogContent,
    ReactiveFormsModule,
    MatLabel,
    MatFormField,
    MatInput,
    MatButton,
    MatDialogActions,
    MatDialogClose,
    MatFormField
  ],
  styleUrls: ['./ingredient-category-form.component.less']
})
export class IngredientCategoryFormComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private categoryService: IngredientCategoryService,
    private dialogRef: MatDialogRef<IngredientCategoryFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IngredientCategory
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required]
    });

    if (data?.id) {
      this.form.patchValue(data);
    }
  }

  save(): void {
    if (this.form.valid) {
      const category = {...this.data, ...this.form.value};

      if (category.id) {
        this.categoryService.update(category.id, category).subscribe(() => {
          this.dialogRef.close(true);
        });
      } else {
        this.categoryService.create(category).subscribe(() => {
          this.dialogRef.close(true);
        });
      }
    }
  }
}
