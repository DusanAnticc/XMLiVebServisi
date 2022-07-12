import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';

@Component({
  selector: 'app-create-confirmation',
  templateUrl: './create-confirmation.component.html',
  styleUrls: ['./create-confirmation.component.scss']
})
export class CreateConfirmationComponent {
  form: FormGroup;
  parser = new x2js.Parser();

  constructor() {
    this.form = new FormGroup({
      imePrezime: new FormControl('', [Validators.required]),
      datumRodjenja: new FormControl('', [Validators.required]),
      pol: new FormControl('', [Validators.required]),
      jmbg: new FormControl('', [Validators.pattern('[0-9]{13}'), Validators.required]),
      vakcina: new FormControl('', [Validators.required]),
    })
   }

  submit() {

  }

}
