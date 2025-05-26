import {Component, OnInit} from '@angular/core';
import {IngredientCategory} from '../../models/ingredient-category.model';
import {IngredientCategoryService} from '../../services/ingredient-category.service';
import {MatDialog} from '@angular/material/dialog';
import {IngredientCategoryFormComponent} from './ingredient-category-form/ingredient-category-form.component';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef, MatHeaderRow,
  MatHeaderRowDef, MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-ingredient-category-list',
  templateUrl: './ingredient-category.component.html',
  imports: [
    MatTable,
    MatHeaderCell,
    MatCell,
    MatHeaderCellDef,
    MatCellDef,
    MatColumnDef,
    MatButton,
    MatIconButton,
    MatIcon,
    MatHeaderRowDef,
    MatRowDef,
    MatHeaderRow,
    MatRow
  ],
  styleUrls: ['./ingredient-category.component.less']
})
export class IngredientCategoryListComponent implements OnInit {
  categories: IngredientCategory[] = [];
  displayedColumns: string[] = ['name', 'actions'];

  constructor(
    private categoryService: IngredientCategoryService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getAll().subscribe(
      data => this.categories = data
    );
  }

  openForm(category?: IngredientCategory): void {
    const dialogRef = this.dialog.open(IngredientCategoryFormComponent, {
      width: '600px',
      data: category || {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadCategories();
    });
  }

  delete(id: string): void {
    if (confirm('Are you sure you want to delete this category?')) {
      this.categoryService.delete(id).subscribe(() => {
        this.loadCategories();
      });
    }
  }
}
