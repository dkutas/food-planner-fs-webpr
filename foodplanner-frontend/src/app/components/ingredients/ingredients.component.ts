import {Component, OnInit} from '@angular/core';
import {NzModalModule, NzModalService} from 'ng-zorro-antd/modal';
import {NzTableComponent} from 'ng-zorro-antd/table';
import {RouterLink} from '@angular/router';
import {NgForOf} from '@angular/common';
import {Ingredient} from '../../models/ingredient.model';
import {IngredientService} from '../../services/ingredient.service';

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredients.component.html',
  imports: [
    NzTableComponent,
    RouterLink,
    NzModalModule,
    NgForOf
  ],
  styleUrls: ['./ingredients.component.less']
})
export class IngredientsComponent implements OnInit {
  ingredients: Ingredient[] = [];

  constructor(
    private ingredientService: IngredientService,
    private modal: NzModalService
  ) {
  }

  ngOnInit(): void {
    this.loadIngredients();
  }

  loadIngredients() {
    this.ingredientService.getAll().subscribe((res: Ingredient[]) => this.ingredients = res);
  }

  deleteRecipe(id: number) {
    this.modal.confirm({
      nzTitle: 'Biztosan törlöd?',
      nzOnOk: () => {
        this.ingredientService.delete(id).subscribe(() => this.loadIngredients());
      }
    });
  }
}
