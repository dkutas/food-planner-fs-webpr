import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {Ingredient} from '../../../models/ingredient.model';
import {PantryService} from '../../../services/pantry.service';
import {IngredientService} from '../../../services/ingredient.service';
import {Pantry, PantryInput} from '../../../models/pantry-model';
import {MatFormField, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatButton} from '@angular/material/button';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-pantry-form',
  templateUrl: './pantry-form.component.html',
  imports: [
    MatDialogTitle,
    MatDialogContent,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatFormField,
    MatSelect,
    MatOption,
    MatFormField,
    MatDialogActions,
    MatDialogClose,
    MatButton,
    NgForOf
  ],
  styleUrls: ['./pantry-form.component.less']
})
export class PantryFormComponent {
  form: FormGroup;
  ingredients: Ingredient[] = [];

  constructor(
    private fb: FormBuilder,
    private pantryService: PantryService,
    private ingredientService: IngredientService,
    private dialogRef: MatDialogRef<PantryFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Pantry
  ) {
    this.form = this.fb.group({
      ingredient: ['', Validators.required]
    });

    if (data?.id) {
      this.form.patchValue(data);
    }

    this.loadIngredients();
  }

  compareIngredients(i1: Ingredient, i2: Ingredient): boolean {
    return i1?.id === i2?.id;
  }

  loadIngredients(): void {
    this.ingredientService.getAll().subscribe(
      data => this.ingredients = data
    );
  }

  save(): void {
    if (this.form.valid) {
      const pantry: PantryInput = {
        ingredientId: this.form.value.ingredient.id,
      }

      if (this.data?.id) {
        this.pantryService.update(this.data.id, pantry).subscribe(() => {
          this.dialogRef.close(true);
        });
      } else {
        this.pantryService.create(pantry).subscribe(() => {
          this.dialogRef.close(true);
        });
      }
    }
  }
}
