import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
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
import {Recipe} from '../../../models/recipe.model';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatButton} from '@angular/material/button';

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
    MatLabel
  ],
  styleUrls: ['./recipe-form.component.less']
})
export class RecipeFormComponent {
  form: FormGroup;
  kitchens: Kitchen[] = [];

  constructor(
    private fb: FormBuilder,
    private recipeService: RecipeService,
    private kitchenService: KitchenService,
    private dialogRef: MatDialogRef<RecipeFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Recipe
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      kitchen: ['', Validators.required]
    });

    if (data?.id) {
      this.form.patchValue(data);
    }

    this.loadKitchens();
  }

  loadKitchens(): void {
    this.kitchenService.getAll().subscribe(
      data => this.kitchens = data
    );
  }

  save(): void {
    if (this.form.valid) {
      const recipe = {...this.data, ...this.form.value};

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
