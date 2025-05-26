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
import {Pantry} from '../../../models/pantry-model';
import {MatFormField, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatButton} from '@angular/material/button';

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
    MatButton
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

  loadIngredients(): void {
    this.ingredientService.getAll().subscribe(
      data => this.ingredients = data
    );
  }

  save(): void {
    if (this.form.valid) {
      const pantry = {...this.data, ...this.form.value};

      if (pantry.id) {
        this.pantryService.update(pantry.id, pantry).subscribe(() => {
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
