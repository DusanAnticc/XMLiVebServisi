import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';
import { ConsentService } from '../../service/consent.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-create-consent',
  templateUrl: './create-consent.component.html',
  styleUrls: ['./create-consent.component.scss']
})
export class CreateConsentComponent {
  form: FormGroup;
  parser = new x2js.Parser();

  constructor(
    public http: HttpClient,
    public consentService : ConsentService
    ) {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.pattern('[0-9]{13}')]),
      zdravstvenaUstanova: new FormControl('',[Validators.required]),
      vakcinacijskiPunkt: new FormControl('',[Validators.required]),
      ime: new FormControl('',[Validators.required]),
      prezime: new FormControl('',[Validators.required]),
      faksimil: new FormControl('',[Validators.required]),
      broj: new FormControl('',[Validators.required]),
      vakcina: new FormControl('', [Validators.required]),
      datumDavanjaVakcine: new FormControl(new Date(),[Validators.required]),
      nacinDavanjaVakcine: new FormControl("IM",[Validators.required]),
      ekstremitet: new FormControl('',[Validators.required]),
      serijaVakcine: new FormControl('',[Validators.required]),
      proizvodjac: new FormControl('',[Validators.required]),
      nezeljenaReakcija: new FormControl('',[Validators.required])
    });

  }

  submit(){
    var obj = {
      Radnikov_deo : {
        '#': {
            Zdravstvena_ustanova: this.form.value.zdravstvenaUstanova,
            Vakc_punkt: this.form.value.vakcinacijskiPunkt,
            Podaci_lekar: {
              Ime:this.form.value.ime,
              Prezime:this.form.value.prezime,
              Faksimil:this.form.value.faksimil,
              Broj_telefona:this.form.value.broj
            },
                          
            Vakcina:{
              Naziv: this.form.value.vakcina,
              Datum_davanja: this.form.value.datumDavanjaVakcine,
              Nacin_davanja: this.form.value.nacinDavanjaVakcine,
              Ekstremitet: this.form.value.ekstremitet,
              Serija: this.form.value.serijaVakcine,
              Proizvodjac:this.form.value.proizvodjac,
              Nuspojava: this.form.value.nezeljenaReakcija
            },
            Privremene_nuspojave:"",
            Odluka:"false"      
        }
      }
    }
    this.consentService.create(obj).subscribe((result: any) => {
      this.parser.parseString(result, function (err: any, res: any) {});
    });
  }
}
