import { Component } from '@angular/core';

import { NzCardModule } from 'ng-zorro-antd/card';

@Component({
  selector: 'app-recipe-card',
  imports: [NzCardModule],
  templateUrl: './recipe-card.component.html'
})
export class RecipeCardComponent {}
