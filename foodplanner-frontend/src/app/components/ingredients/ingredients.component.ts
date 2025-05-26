import {Component, OnInit} from '@angular/core';
import {Ingredient} from '../../models/ingredient.model';
import {IngredientService} from '../../services/ingredient.service';
import {MatDialog} from '@angular/material/dialog';
import {IngredientFormComponent} from './ingredient-form/ingredient-form.component';
import {MatButton, MatIconButton} from '@angular/material/button';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef, MatHeaderRow,
  MatHeaderRowDef, MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-ingredient-list',
  imports: [
    MatButton,
    MatTable,
    MatHeaderCell,
    MatCell,
    MatColumnDef,
    MatHeaderCellDef,
    MatCellDef,
    MatIconButton,
    MatIcon,
    MatHeaderRowDef,
    MatRowDef,
    MatRow,
    MatHeaderRow
  ],
  templateUrl: './ingredients.component.html'
})
export class IngredientListComponent implements OnInit {
  ingredients: Ingredient[] = [];
  displayedColumns: string[] = ['name', 'category', 'actions'];

  constructor(
    private ingredientService: IngredientService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadIngredients();
  }

  loadIngredients(): void {
    this.ingredientService.getAll().subscribe(
      data => this.ingredients = data
    );
  }

  openForm(ingredient?: Ingredient): void {
    const dialogRef = this.dialog.open(IngredientFormComponent, {
      width: '600px',
      data: ingredient || {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadIngredients();
    });
  }

  delete(id: string): void {
    if (confirm('Are you sure?')) {
      this.ingredientService.delete(id).subscribe(() => {
        this.loadIngredients();
      });
    }
  }
}
