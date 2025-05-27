import {Component, OnInit} from '@angular/core';
import {IngredientCategory} from '../../models/ingredient-category.model';
import {IngredientCategoryService} from '../../services/ingredient-category.service';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef, MatHeaderRow,
  MatHeaderRowDef, MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';

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
    MatHeaderRowDef,
    MatRowDef,
    MatHeaderRow,
    MatRow
  ],
  styleUrls: ['./ingredient-category.component.less']
})
export class IngredientCategoryListComponent implements OnInit {
  categories: IngredientCategory[] = [];
  displayedColumns: string[] = ['name'];

  constructor(
    private categoryService: IngredientCategoryService,
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
}
