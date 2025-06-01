import {Component, OnInit} from '@angular/core';
import {ShoppingList} from '../../models/shopping-list.model';
import {ShoppingListService} from '../../services/shopping-list.service';
import {MatDialog} from '@angular/material/dialog';
import {ShoppingListFormComponent} from './shopping-list-form/shopping-list-form.component';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIcon, MatIconModule} from '@angular/material/icon';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatList, MatListItem} from '@angular/material/list';
import {KeyValuePipe, NgForOf} from '@angular/common';
import {MatToolbarRow} from '@angular/material/toolbar';
import {IngredientService} from '../../services/ingredient.service';
import {add} from 'date-fns';
import {PantryService} from '../../services/pantry.service';

@Component({
  selector: 'app-shopping-list-list',
  templateUrl: './shopping-list.component.html',
  imports: [
    MatButton,
    MatIconButton,
    MatIcon,
    MatIconModule,
    MatCard,
    MatCardHeader,
    MatCardContent,
    MatList,
    MatListItem,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardContent,
    MatList,
    MatListItem,
    MatIcon,
    MatButton,
    MatIconButton,
    NgForOf,
    KeyValuePipe,
    MatToolbarRow
  ],
  styleUrls: ['./shopping-list.component.less']
})
export class ShoppingListListComponent implements OnInit {
  shoppingList: ShoppingList[] = [];
  groupedShoppingList: Map<string, ShoppingList[]> = new Map();

  constructor(
    private shoppingListService: ShoppingListService,
    private dialog: MatDialog,
    private pantryService: PantryService,
    private ingredientService: IngredientService
  ) {
  }

  ngOnInit(): void {
    this.loadShoppingList();
  }

  loadShoppingList(): void {

    this.shoppingListService.getAll().subscribe(data => {
      this.shoppingList = data;
      // Group shopping list items by ingredient category
      this.groupedShoppingList = new Map(
        Object.entries(
          this.shoppingList.reduce((groups, item) => {
            const category = item.ingredient.category?.name || 'Uncategorized';
            return {
              ...groups,
              [category]: [...(groups[category] || []), item]
            };
          }, {} as Record<string, ShoppingList[]>)
        )
      );
    });
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

  handleShoppingDone(): void {
    this.shoppingList.forEach(item => {
      this.pantryService.create({ingredientId: item.ingredient.id}).subscribe();
      this.shoppingListService.delete(item.id).subscribe();
    });
    this.loadShoppingList();
  }

  handleShoppingReset() {
    if (confirm('Are you sure you want to reset the shopping list?')) {
      this.shoppingListService.getAll().subscribe(data => {
        data.forEach(item => {
          this.shoppingListService.delete(item.id).subscribe();
        });
        this.loadShoppingList();
      });
    }
  }


  delete(id: string): void {
    if (confirm('Are you sure you want to delete this item?')) {
      this.shoppingListService.delete(id).subscribe(() => {
        this.loadShoppingList();
      });
    }
  }
}
