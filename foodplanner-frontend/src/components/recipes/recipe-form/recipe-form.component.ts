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
import {AsyncPipe, NgForOf} from '@angular/common';
import {Ingredient} from '../../../models/ingredient.model';
import {IngredientService} from '../../../services/ingredient.service';
import {map, Observable, startWith} from 'rxjs';
import {MatAutocomplete, MatAutocompleteSelectedEvent, MatAutocompleteTrigger} from '@angular/material/autocomplete';
import {MatChipGrid, MatChipInput, MatChipRemove, MatChipRow} from '@angular/material/chips';
import {MatIcon} from '@angular/material/icon';
import {MatCheckbox} from '@angular/material/checkbox';
import {MatHint} from '@angular/material/form-field';


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
    MatHint,
    NgForOf,
    MatAutocomplete,
    MatAutocompleteTrigger,
    MatChipInput,
    MatIcon,
    MatChipGrid,
    MatChipRemove,
    AsyncPipe,
    MatChipRow,
    MatCheckbox
  ],
  styleUrls: ['./recipe-form.component.less']
})
export class RecipeFormComponent {
  form: FormGroup;
  kitchens: Kitchen[] = [];
  ingredients: Ingredient[] = [];
  ingredientsControl = new FormControl('');
  filteredIngredients: Observable<Ingredient[]>;
  selectedIngredients: Ingredient[] = [];

  constructor(
    private fb: FormBuilder,
    private recipeService: RecipeService,
    private kitchenService: KitchenService,
    private dialogRef: MatDialogRef<RecipeFormComponent>,
    private ingredientService: IngredientService,
    @Inject(MAT_DIALOG_DATA) public data: Recipe
  ) {
    this.form = this.fb.group({
      id: [data?.id || null],
      name: ['', Validators.required],
      description: [''],
      kitchen: [{}, Validators.required],
      ingredients: [[], Validators.required],
      preparationTime: [0, [Validators.required, Validators.min(1)]],
      isPublic: [false],
    });

    if (data?.id) {
      this.form.patchValue({...data, isPublic: true});
      this.selectedIngredients = data.ingredients || [];
    }

    this.filteredIngredients = this.ingredientsControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const searchTerm = typeof value === 'string' ? value : '';
        return this._filter(searchTerm);
      })
    );

    this.loadKitchens();
    this.loadIngredients();
  }

  private _filter(value: string): Ingredient[] {
    const filterValue = value.toLowerCase();
    return this.ingredients.filter(ingredient =>
      ingredient.name.toLowerCase().includes(filterValue) &&
      !this.selectedIngredients.find(i => i.id === ingredient.id)
    );
  }

  removeIngredient(ingredient: Ingredient): void {
    const index = this.selectedIngredients.indexOf(ingredient);
    if (index >= 0) {
      this.selectedIngredients.splice(index, 1);
      this.form.patchValue({ingredients: this.selectedIngredients});
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    const ingredient = event.option.value as Ingredient;
    if (!this.selectedIngredients.find(i => i.id === ingredient.id)) {
      this.selectedIngredients.push(ingredient);
      this.form.patchValue({ingredients: this.selectedIngredients});
    }
    this.ingredientsControl.setValue('');
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

  compareKitchens(kitchen1: Kitchen, kitchen2: Kitchen): boolean {
    return kitchen1 && kitchen2 ? kitchen1.id === kitchen2.id : kitchen1 === kitchen2;
  }

  save(): void {
    if (this.form.valid) {
      const recipe: RecipeInput = {
        id: this.form.value.id,
        name: this.form.value.name,
        description: this.form.value.description,
        ingredientIds: this.form.value.ingredients.map((i: Ingredient) => i.id),
        kitchenId: this.form.value.kitchen.id,
        preparationTime: this.form.value.preparationTime,
        isPublic: this.form.value.isPublic
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
