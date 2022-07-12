import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';

@Component({
  selector: 'app-create-consent',
  templateUrl: './create-consent.component.html',
  styleUrls: ['./create-consent.component.scss']
})
export class CreateConsentComponent {
  form: FormGroup;
  parser = new x2js.Parser();

  constructor() {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.pattern('[0-9]{13}')]),
      zdravstvenaUstanova: new FormControl('',[Validators.required]),
      vakcinacijskiPunkt: new FormControl('',[Validators.required]),
      podaciOLekaru: new FormControl('',[Validators.required]),
      vakcina: new FormControl('', [Validators.required]),
      datumDavanjaVakcine: new FormControl(new Date(),[Validators.required]),
      nacinDavanjaVakcine: new FormControl("IM",[Validators.required]),
      ekstremitet: new FormControl('',[Validators.required]),
      serijaVakcine: new FormControl('',[Validators.required]),
      proizvodjac: new FormControl('',[Validators.required]),
      nezeljenaReakcija: new FormControl('',[Validators.required])
    });

  }

  submit() {

  }
}
