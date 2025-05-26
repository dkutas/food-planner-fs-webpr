import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {MatButton} from '@angular/material/button';
import {MatFormField, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {Ingredient} from '../../../models/ingredient.model';
import {ShoppingListService} from '../../../services/shopping-list.service';
import {IngredientService} from '../../../services/ingredient.service';
import {ShoppingList} from '../../../models/shopping-list.model';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-shopping-list-form',
  templateUrl: './shopping-list-form.component.html',
  imports: [
    MatDialogTitle,
    MatDialogContent,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatSelect,
    MatOption,
    MatDialogActions,
    MatDialogClose,
    MatButton,
    NgForOf
  ],
  styleUrls: ['./shopping-list-form.component.less']
})
export class ShoppingListFormComponent {
  form: FormGroup;
  ingredients: Ingredient[] = [];

  constructor(
    private fb: FormBuilder,
    private shoppingListService: ShoppingListService,
    private ingredientService: IngredientService,
    private dialogRef: MatDialogRef<ShoppingListFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ShoppingList
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
      const shoppingListItem = {...this.data, ...this.form.value};

      if (shoppingListItem.id) {
        this.shoppingListService.update(shoppingListItem.id, shoppingListItem).subscribe(() => {
          this.dialogRef.close(true);
        });
      } else {
        this.shoppingListService.create(shoppingListItem).subscribe(() => {
          this.dialogRef.close(true);
        });
      }
    }
  }
}
