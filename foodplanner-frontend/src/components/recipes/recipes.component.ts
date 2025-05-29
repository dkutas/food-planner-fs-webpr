import {Component, OnInit} from '@angular/core';
import {Recipe} from '../../models/recipe.model';
import {RecipeService} from '../../services/recipe.service';
import {MatDialog} from '@angular/material/dialog';
import {RecipeFormComponent} from './recipe-form/recipe-form.component';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {RecipeDetailsComponent} from './recipe-details/recipe-details.component';
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardImage,
  MatCardTitle,
  MatCardSubtitle,
} from '@angular/material/card';
import {KeyValuePipe, NgForOf} from '@angular/common';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipes.component.html',
  imports: [
    MatIconButton,
    MatIcon,
    MatButton,
    MatCardActions,
    MatCard,
    MatCardHeader,
    MatCardContent,
    MatCardImage,
    MatCardTitle,
    NgForOf,
    KeyValuePipe,
    MatCardSubtitle
  ],
  styleUrls: ['./recipes.component.less']
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[] = [];
  groupedRecipes: Map<string, Recipe[]> = new Map();

  constructor(
    private recipeService: RecipeService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadRecipes();
  }

  loadRecipes(): void {
    this.recipeService.getAll().subscribe(data => {
      this.recipes = data;
      // Group recipes by kitchen
      this.groupedRecipes = new Map(
        Object.entries(
          this.recipes.reduce((groups, recipe) => {
            const kitchen = recipe.kitchen?.name || 'Other';
            return {
              ...groups,
              [kitchen]: [...(groups[kitchen] || []), recipe]
            };
          }, {} as Record<string, Recipe[]>)
        )
      );
    });
  }

  openDetails(recipe: Recipe): void {
    this.dialog.open(RecipeDetailsComponent, {
      width: '800px',
      data: recipe
    });
  }

  openForm(recipe?: Recipe): void {
    const dialogRef = this.dialog.open(RecipeFormComponent, {
      width: '600px',
      data: recipe || {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadRecipes();
      }
    });
  }

  delete(id: string): void {
    if (confirm('Are you sure you want to delete this recipe?')) {
      this.recipeService.delete(id).subscribe(() => {
        this.loadRecipes();
      });
    }
  }
}
