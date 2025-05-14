import {Component, OnInit} from '@angular/core';
import {GridComponent} from './grid/grid.component';
import {PantryService} from '../../services/pantry.service';
import {Pantry} from '../../models/pantry-model';

@Component({
  selector: 'app-pantry',
  templateUrl: './pantry.component.html',
  imports: [
    GridComponent
  ],
  styleUrls: ['./pantry.component.less']
})
export class PantryComponent implements OnInit {

  pantries: Pantry[] = [];

  constructor(
    private pantryService: PantryService
  ) {
  }

  ngOnInit(): void {
    this.loadPantries();
  }

  loadPantries() {
    this.pantryService.getAll().subscribe(res => this.pantries = res);
  }

}
