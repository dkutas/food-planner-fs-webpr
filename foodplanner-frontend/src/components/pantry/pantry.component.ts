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
import {KeyValuePipe, NgForOf} from '@angular/common';
import {MatList, MatListItem} from '@angular/material/list';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from '@angular/material/card';

@Component({
  selector: 'app-pantry-list',
  templateUrl: './pantry.component.html',
  imports: [
    MatButton,
    MatIconButton,
    MatIcon,
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
    KeyValuePipe

  ],
  styleUrls: ['./pantry.component.less']
})
export class PantryListComponent implements OnInit {
  pantries: Pantry[] = [];
  groupedPantries: Map<string, Pantry[]> = new Map();
  
  constructor(
    private pantryService: PantryService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadPantries();
  }


  loadPantries(): void {
    this.pantryService.getAll().subscribe(data => {
      this.pantries = data;
      // Group pantry items by ingredient category
      this.groupedPantries = new Map(
        Object.entries(
          this.pantries.reduce((groups, item) => {
            const category = item.ingredient.category?.name || 'Uncategorized';
            return {
              ...groups,
              [category]: [...(groups[category] || []), item]
            };
          }, {} as Record<string, Pantry[]>)
        )
      );
    });
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
