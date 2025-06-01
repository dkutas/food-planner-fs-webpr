import {Component, Inject} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialogActions, MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {MatButton} from '@angular/material/button';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatOption} from '@angular/material/select';
import {Ingredient} from '../../../models/ingredient.model';
import {ShoppingListService} from '../../../services/shopping-list.service';
import {IngredientService} from '../../../services/ingredient.service';
import {ShoppingList, ShoppingListInput} from '../../../models/shopping-list.model';
import {AsyncPipe, NgForOf} from '@angular/common';
import {MatAutocomplete, MatAutocompleteTrigger} from '@angular/material/autocomplete';
import {map, Observable, startWith} from 'rxjs';

@Component({
  selector: 'app-shopping-list-form',
  templateUrl: './shopping-list-form.component.html',
  imports: [
    MatDialogTitle,
    MatDialogContent,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatOption,
    MatDialogActions,
    MatDialogClose,
    MatButton,
    NgForOf,
    MatAutocompleteTrigger,
    MatAutocomplete,
    AsyncPipe,
    MatInput
  ],
  styleUrls: ['./shopping-list-form.component.less']
})
export class ShoppingListFormComponent {
  form: FormGroup;
  ingredients: Ingredient[] = [];
  ingredientControl = new FormControl('');
  filteredIngredients: Observable<Ingredient[]>;

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

    this.filteredIngredients = this.ingredientControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = value;
        return name ? this._filter(name) : this.ingredients.slice();
      })
    );

    if (data?.id) {
      this.form.patchValue(data);
      this.ingredientControl.setValue(data.ingredient.name);
    }

    this.loadIngredients();
  }

  loadIngredients(): void {
    this.ingredientService.getAll().subscribe(
      data => this.ingredients = data
    );
  }

  displayFn(ingredient: Ingredient): string {
    return ingredient?.name ?? '';
  }

  private _filter(name: string): Ingredient[] {
    const filterValue = name.toLowerCase();
    return this.ingredients.filter(ingredient =>
      ingredient.name.toLowerCase().includes(filterValue)
    );
  }

  compareIngredients(i1: Ingredient, i2: Ingredient): boolean {
    return i1?.id === i2?.id;
  }

  save(): void {
    if (this.form.valid) {
      const shoppingListItem: ShoppingListInput = {
        ingredientId: this.form.value.ingredient.id,
      };

      if (this.data?.id) {
        this.shoppingListService.update(this.data.id, shoppingListItem).subscribe(() => {
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
