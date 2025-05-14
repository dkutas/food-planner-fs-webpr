import {Component, OnInit} from '@angular/core';
import {GridComponent} from './grid/grid.component';
import {RecipeService} from '../../services/recipe.service';
import {Recipe} from '../../models/recipe.model';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  imports: [
    GridComponent
  ],
  styleUrls: ['./recipes.component.less']
})
export class RecipeComponent implements OnInit {

  recipes: Recipe[] = [];

  constructor(
    private recipeService: RecipeService,
  ) {
  }

  ngOnInit(): void {
    this.loadRecipe();
  }

  loadRecipe() {
    this.recipeService
      .getAll().subscribe((res: Recipe[]) => this.recipes = res);
  }


}
