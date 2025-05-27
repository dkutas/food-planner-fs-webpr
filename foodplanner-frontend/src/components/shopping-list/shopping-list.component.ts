import {Component, OnInit} from '@angular/core';
import {ShoppingList} from '../../models/shopping-list.model';
import {ShoppingListService} from '../../services/shopping-list.service';
import {MatDialog} from '@angular/material/dialog';
import {ShoppingListFormComponent} from './shopping-list-form/shopping-list-form.component';
import {MatButton, MatIconButton} from '@angular/material/button';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable
} from '@angular/material/table';
import {MatIcon, MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-shopping-list-list',
  templateUrl: './shopping-list.component.html',
  imports: [
    MatButton,
    MatTable,
    MatHeaderCell,
    MatCell,
    MatCellDef,
    MatHeaderCellDef,
    MatColumnDef,
    MatIconButton,
    MatIcon,
    MatHeaderRow,
    MatRowDef,
    MatHeaderRowDef,
    MatIconModule,
    MatRow
  ],
  styleUrls: ['./shopping-list.component.less']
})
export class ShoppingListListComponent implements OnInit {
  shoppingList: ShoppingList[] = [];
  displayedColumns: string[] = ['ingredient', 'actions'];

  constructor(
    private shoppingListService: ShoppingListService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadShoppingList();
  }

  loadShoppingList(): void {
    this.shoppingListService.getAll().subscribe(
      data => this.shoppingList = data
    );
  }

  openForm(item?: ShoppingList): void {
    const dialogRef = this.dialog.open(ShoppingListFormComponent, {
      width: '600px',
      data: item || {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadShoppingList();
    });
  }

  delete(id: string): void {
    if (confirm('Are you sure you want to delete this item?')) {
      this.shoppingListService.delete(id).subscribe(() => {
        this.loadShoppingList();
      });
    }
  }
}
