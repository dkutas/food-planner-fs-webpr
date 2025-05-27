import {Component, OnInit} from '@angular/core';
import {Pantry} from '../../models/pantry-model';
import {PantryService} from '../../services/pantry.service';
import {MatDialog} from '@angular/material/dialog';
import {PantryFormComponent} from './pantry-form/pantry-form.component';
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
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-pantry-list',
  templateUrl: './pantry.component.html',
  imports: [
    MatButton,
    MatTable,
    MatColumnDef,
    MatHeaderCellDef,
    MatHeaderCell,
    MatCellDef,
    MatCell,
    MatIconButton,
    MatIcon,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRowDef,
    MatRow
  ],
  styleUrls: ['./pantry.component.less']
})
export class PantryListComponent implements OnInit {
  pantries: Pantry[] = [];
  displayedColumns: string[] = ['ingredient', 'actions'];

  constructor(
    private pantryService: PantryService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadPantries();
  }

  loadPantries(): void {
    this.pantryService.getAll().subscribe(
      data => this.pantries = data
    );
  }

  openForm(pantry?: Pantry): void {
    const dialogRef = this.dialog.open(PantryFormComponent, {
      width: '600px',
      data: pantry || {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadPantries();
    });
  }

  delete(id: string): void {
    if (confirm('Are you sure you want to delete this pantry item?')) {
      this.pantryService.delete(id).subscribe(() => {
        this.loadPantries();
      });
    }
  }
}
