import {Component, OnInit} from '@angular/core';
import {Kitchen} from '../../models/kitchen.model';
import {KitchenService} from '../../services/kitchen.service';
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from '@angular/material/table';

@Component({
  selector: 'app-kitchen-list',
  templateUrl: './kitchen.component.html',
  imports: [
    MatTable,
    MatHeaderCell,
    MatCell,
    MatColumnDef,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatHeaderCellDef,
    MatCellDef,
    MatHeaderRowDef
  ],
  styleUrls: ['./kitchen.component.less']
})
export class KitchenListComponent implements OnInit {
  kitchens: Kitchen[] = [];
  displayedColumns: string[] = ['name'];

  constructor(
    private kitchenService: KitchenService,
  ) {
  }

  ngOnInit(): void {
    this.loadKitchens();
  }

  loadKitchens(): void {
    this.kitchenService.getAll().subscribe(
      data => this.kitchens = data
    );
  }


}
