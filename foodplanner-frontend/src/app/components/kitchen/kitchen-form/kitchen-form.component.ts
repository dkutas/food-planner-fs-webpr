import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MAT_DIALOG_DATA, MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {KitchenService} from '../../../services/kitchen.service';
import {Kitchen} from '../../../models/kitchen.model';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-kitchen-form',
  templateUrl: './kitchen-form.component.html',
  imports: [
    MatDialogContent,
    MatFormField,
    ReactiveFormsModule,
    MatDialogTitle,
    MatFormField,
    MatLabel,
    MatInput,
    MatButton,
    MatDialogClose,
    MatDialogActions
  ],
  styleUrls: ['./kitchen-form.component.less']
})
export class KitchenFormComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private kitchenService: KitchenService,
    private dialogRef: MatDialogRef<KitchenFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Kitchen
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required]
    });

    if (data?.id) {
      this.form.patchValue(data);
    }
  }

  save(): void {
    if (this.form.valid) {
      const kitchen = {...this.data, ...this.form.value};

      if (kitchen.id) {
        this.kitchenService.update(kitchen.id, kitchen).subscribe(() => {
          this.dialogRef.close(true);
        });
      } else {
        this.kitchenService.create(kitchen).subscribe(() => {
          this.dialogRef.close(true);
        });
      }
    }
  }
}
