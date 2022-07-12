import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-response-modal',
  templateUrl: './response-modal.component.html',
  styleUrls: ['./response-modal.component.scss'],
})
export class ResponseModalComponent implements OnInit {
  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<ResponseModalComponent>) {
    this.form = new FormGroup({
      razlog: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {}

  closeDialog() {
    this.dialogRef.close({ event: 'close' });
  }

  save() {
    this.dialogRef.close({ event: 'send', data: this.form.value.razlog });
  }
}
