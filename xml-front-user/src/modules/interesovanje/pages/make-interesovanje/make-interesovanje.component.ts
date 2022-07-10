import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';

import { InteresovanjeService } from '../../services/interesovanje.service';
import * as moment from 'moment';
import * as x2js from 'xml2js';

@Component({
  selector: 'app-make-interesovanje',
  templateUrl: './make-interesovanje.component.html',
  styleUrls: ['./make-interesovanje.component.scss']
})
export class MakeInteresovanjeComponent implements OnInit {

  form: FormGroup;
  parser = new x2js.Parser();
  options: any[] = [
    { value: 'Drzavljanin Republike Srbije', viewValue: 'Državljanin Republike Srbije' },
    { value: 'Strani drzavljanin sa boravkom u RS', viewValue: 'Strani državljanin sa boravkom u RS' },
    { value: 'Strani drzavljanin bez boravka u RS', viewValue: 'Strani državljanin bez boravka u RS' },
  ];
  vaccines: any[] = [
    { value: 'Pfizer' },
    { value: 'Sputnik V' },
    { value: 'Sinopharm' },
    { value: 'AstraZeneca' },
    { value: 'Moderna' },
    { value: 'Bilo koja' },
  ];
  yesNo: any[] = [
    { value: 'Da' },
    { value: 'Ne' },

  ];
  opstine: any[] = [
    { value: 'Novi Sad' },
    { value: 'Beograd' },
    { value: 'Nis' },
  ];

  constructor(private interesovanjeService: InteresovanjeService) {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{13}')]),
      ime: new FormControl('', [Validators.required]),
      prezime: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      brMobilni: new FormControl('', [Validators.required, Validators.pattern('06[0-9 ]{8,9}')]),
      brFiksni: new FormControl('', [Validators.required, Validators.pattern('0[0-9 ]{8}')]),
      opcija: new FormControl('', [Validators.required]),
      vakcina: new FormControl('', [Validators.required]),
      davalac: new FormControl('', [Validators.required]),
      opstina: new FormControl('', [Validators.required]),

    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    var obj = {
      Interesovanje: {
        '#': {
          Opcija: this.form.get('opcija')?.value,
          Licni_podaci: {
            Jmbg: this.form.get('jmbg')?.value,
            Ime: this.form.get('ime')?.value,
            Prezime: this.form.get('prezime')?.value,
            Kontakt: {
                Email: this.form.get('email')?.value,
                Broj_mobilnog: this.form.get('brMobilni')?.value,
                Broj_fiksnog: this.form.get('brFiksni')?.value,
            },
            Opstina: this.form.get('opstina')?.value,
          },
          Izabrane_vakcine: {            
            Vakcina: this.form.get('vakcina')?.value,            
          },
          Davalac_krvi: this.form.get('davalac')?.value,
          Datum: (moment(new Date())).format('YYYY-MM-DD')
        }
      }
    }; 
    this.interesovanjeService.create(obj).subscribe(
      (result: any) => {
        this.parser.parseString(result, function (err: any, res: any) {
        });
      }
    )
  }

}