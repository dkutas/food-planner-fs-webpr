import {Component, OnInit} from '@angular/core';
import {Kitchen} from '../../models/kitchen.model';
import {KitchenService} from '../../services/kitchen.service';
import {MatDialog} from '@angular/material/dialog';
import {KitchenFormComponent} from './kitchen-form/kitchen-form.component';
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
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-kitchen-list',
  templateUrl: './kitchen.component.html',
  imports: [
    MatTable,
    MatHeaderCell,
    MatCell,
    MatColumnDef,
    MatIcon,
    MatIconButton,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatButton,
    MatHeaderCellDef,
    MatCellDef,
    MatHeaderRowDef
  ],
  styleUrls: ['./kitchen.component.less']
})
export class KitchenListComponent implements OnInit {
  kitchens: Kitchen[] = [];
  displayedColumns: string[] = ['name', 'actions'];

  constructor(
    private kitchenService: KitchenService,
    private dialog: MatDialog
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

  openForm(kitchen?: Kitchen): void {
    const dialogRef = this.dialog.open(KitchenFormComponent, {
      width: '600px',
      data: kitchen || {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadKitchens();
    });
  }

  delete(id: string): void {
    if (confirm('Are you sure you want to delete this kitchen?')) {
      this.kitchenService.delete(id).subscribe(() => {
        this.loadKitchens();
      });
    }
  }
}
