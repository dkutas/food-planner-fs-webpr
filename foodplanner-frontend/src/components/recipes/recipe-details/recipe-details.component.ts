// recipe-details.component.ts
import {NgForOf} from '@angular/common';
import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogTitle
} from '@angular/material/dialog';
import {MatButton} from '@angular/material/button';
import {Recipe} from '../../../models/recipe.model';

@Component({
  selector: 'app-recipe-details',
  templateUrl: 'recipe-details.component.html',
  styleUrls: ['recipe-details.component.less'],
  imports: [
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatButton,
    MatDialogClose,
    NgForOf
  ]
})
export class RecipeDetailsComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Recipe
  ) {
  }
}
