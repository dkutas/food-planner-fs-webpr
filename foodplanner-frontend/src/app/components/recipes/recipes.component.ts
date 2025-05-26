import {Component, OnInit} from '@angular/core';
import {Recipe} from '../../models/recipe.model';
import {RecipeService} from '../../services/recipe.service';
import {MatDialog} from '@angular/material/dialog';
import {RecipeFormComponent} from './recipe-form/recipe-form.component';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipes.component.html',
  imports: [
    MatTable,
    MatColumnDef,
    MatHeaderCellDef,
    MatHeaderCell,
    MatCellDef,
    MatCell,
    MatIconButton,
    MatIcon,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRowDef,
    MatRow,
    MatButton
  ],
  styleUrls: ['./recipes.component.less']
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[] = [];
  displayedColumns: string[] = ['name', 'description', 'kitchen', 'actions'];

  constructor(
    private recipeService: RecipeService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadRecipes();
  }

  loadRecipes(): void {
    this.recipeService.getAll().subscribe(
      data => this.recipes = data
    );
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
