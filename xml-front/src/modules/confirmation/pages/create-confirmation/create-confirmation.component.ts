import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService } from '../../service/confirmation.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import * as x2js from 'xml2js';

@Component({
  selector: 'app-create-confirmation',
  templateUrl: './create-confirmation.component.html',
  styleUrls: ['./create-confirmation.component.scss']
})
export class CreateConfirmationComponent {
  form: FormGroup;
  parser = new x2js.Parser();

  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  private serializer = new XMLSerializer();

  constructor(
    public http: HttpClient,
    public confirmationService: ConfirmationService
  ) {
    this.form = new FormGroup({
      ime: new FormControl('', [Validators.required]),
      prezime: new FormControl('', [Validators.required]),
      datumRodjenja: new FormControl('', [Validators.required]),
      pol: new FormControl('', [Validators.required]),
      jmbg: new FormControl('', [Validators.pattern('[0-9]{13}'), Validators.required]),
      ustanova: new FormControl('', [Validators.required]),
    })
   }

  doze: { datum_davanja: Date; serija: string; }[] = [];
  displayedColumns: string[] = ['datum_davanja', 'serija'];

  ngOnInit(): void {
  }

  submit(): void {

    const dozeXml: { doza: { datum_davanja: Date; serija: string; }; }[] = [];

    const potvrda = {
      Potvrda: {
        Licni_podaci: {
          Ime: this.form.value.ime,
          Prezime: this.form.value.prezime,
          Datum_rodjenja: this.form.value.datumRodjenja,
          Pol: this.form.value.pol,
          Jmbg: this.form.value.jmbg
        },
        Primljene_doze: {
          Doze: dozeXml,
        },        
        Naziv_vakcine: "",
        Zdravstvena_ustanova: "ustanova",
        Izdavalac_potvrde: this.form.value.ustanova,
        Datum_izdavanja: new Date(),
        QR_kod: "kod"
      }
    };
    console.log(potvrda);

    this.confirmationService.create(potvrda).subscribe((result: any) => {
      this.parser.parseString(result, function (err: any, res: any) {});
    });
  }

  
}
