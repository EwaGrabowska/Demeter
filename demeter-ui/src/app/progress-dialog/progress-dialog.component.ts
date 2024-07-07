import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-progress-dialog',
  templateUrl: './progress-dialog.component.html',
  styleUrls: ['./progress-dialog.component.css']
})
export class ProgressDialogComponent {
  constructor(
      public dialogRef: MatDialogRef<ProgressDialogComponent>,
      @Inject(MAT_DIALOG_DATA) public data: { message: string, showSpinner: boolean }
  ) {}

  get message(): string {
    return this.data.message;
  }

  onCancel(): void {
    this.dialogRef.close({ canceled: true });
  }
}
