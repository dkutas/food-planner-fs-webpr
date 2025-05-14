import {Component, OnInit} from '@angular/core';
import {GridComponent} from './grid/grid.component';
import {ShoppingList} from '../../models/shopping-list.model';
import {ShoppingListService} from '../../services/shopping-list.service';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.less'],
  imports: [GridComponent, GridComponent]
})
export class ShoppingListComponent implements OnInit {

  shoppingLists: ShoppingList[] = [];

  constructor(
    private shoppingListService: ShoppingListService,
  ) {
  }

  ngOnInit(): void {
    this.loadShoppingLists();
  }

  loadShoppingLists() {
    this.shoppingListService
      .getAll().subscribe((res: ShoppingList[]) => this.shoppingLists = res);
  }


}
