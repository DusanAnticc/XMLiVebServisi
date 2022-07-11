import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import * as x2js from 'xml2js';
import { ZahtevService } from '../../services/zahtev.service';

@Component({
  selector: 'app-make-zahtev',
  templateUrl: './make-zahtev.component.html',
  styleUrls: ['./make-zahtev.component.scss']
})

export class MakeZahtevComponent implements OnInit {

  form: FormGroup;
  parser = new x2js.Parser();

  options: any[] = [
    { value: 'Muski', viewValue: 'Muško' },
    { value: 'Zenski', viewValue: 'Žensko' },
  ];

  opstine: any[] = [
    { value: 'Novi Sad' },
    { value: 'Beograd' },
    { value: 'Nis' },
  ];

  constructor(private zahtevService: ZahtevService) {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{13}')]),
      ime: new FormControl('', [Validators.required]),
      prezime: new FormControl('', [Validators.required]),
      datum: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{4}-[0-9 ]{2}-[0-9]{2}')]),
      brPasosa: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{9}')]),
      pol: new FormControl('', [Validators.required]),
      razlog: new FormControl('', [Validators.required]),
      opstina: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    var obj = {
      Zahtev: {
        '#': {
          Licni_podaci: {
            Ime: this.form.get('ime')?.value,
            Prezime: this.form.get('prezime')?.value,
            Datum_rodjenja: this.form.get('datum')?.value,
            Pol: this.form.get('pol')?.value,
            Jmbg: this.form.get('jmbg')?.value,
            Br_pasosa: this.form.get('brPasosa')?.value,
          },
          Razlog: this.form.get('razlog')?.value,
          Datum_podnosenja: (moment(new Date())).format('YYYY-MM-DD'),
          Mesto: this.form.get('opstina')?.value,
        }
      }
    };
    console.log(obj);
    this.zahtevService.create(obj).subscribe(
      (result) => {
        this.parser.parseString(result, function (err: any, res: any) {
        });
        
      },
      (error) => {
        if (error.status === 400) {
          
        }
        else {
         
        }
      }
    )
  }

}