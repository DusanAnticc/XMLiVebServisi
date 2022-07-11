import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';
import { SaglasnostService } from '../../services/saglasnost.service';
import * as moment from 'moment';

@Component({
  selector: 'app-make-saglasnost',
  templateUrl: './make-saglasnost.component.html',
  styleUrls: ['./make-saglasnost.component.scss'],
})
export class MakeSaglasnostComponent {
  form: FormGroup;
  parser = new x2js.Parser();
  
  constructor(private saglasnostService: SaglasnostService) {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.pattern('[0-9]{13}')]),
      stranoDrzavljanstvo: new FormControl(''),
      brPasosa: new FormControl('', [Validators.pattern('[0-9]{9}')]),
      imeRoditelja: new FormControl('', [Validators.required]),
      datum: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]{4}-[0-9]{2}-[0-9]{2}'),
      ]),
      mestoRodjenja: new FormControl('', [Validators.required]),
      ime: new FormControl('', [Validators.required]),
      prezime: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      brMobilni: new FormControl('', [
        Validators.required,
        Validators.pattern('06[0-9]{8,9}'),
      ]),
      brFiksni: new FormControl('', [
        Validators.required,
        Validators.pattern('0[0-9]{8}'),
      ]),
      vakcina: new FormControl('', [Validators.required]),

      opstina: new FormControl('', [Validators.required]),
      naselje: new FormControl('', [Validators.required]),
      ulica: new FormControl('', [Validators.required]),
      broj: new FormControl('', [Validators.required]),
      opstinaSedistaZastite: new FormControl('', []),
      mestoSedistaZastite: new FormControl('', []),
      radniStatus: new FormControl('', []),
      zanimanjeZaposlenog: new FormControl('', []),
      korisnikSZ: new FormControl('', [Validators.required]),
      saglasan: new FormControl('', [Validators.required]),
      opcija: new FormControl(''),
      pol: new FormControl('', [Validators.required]),
      nazivLekara: new FormControl('', [Validators.required]),
    });
  }

  submit(): void {
    var obj = {
      Saglasnost: {
        '#': {
          Pacijentov_deo: {
            Drzavljanstvo: this.form.get('opcija')?.value,
            Ime_roditelja: this.form.get('imeRoditelja')?.value,
            Mesto_rodjenja: this.form.get('mestoRodjenja')?.value,
            Adresa: {
              Ulica: this.form.get('ulica')?.value,
              Mesto: this.form.get('opstina')?.value,
              Broj: this.form.get('broj')?.value,
            },
            Licni_podaci: {
              Jmbg: this.form.get('jmbg')?.value,
              Prezime: this.form.get('prezime')?.value,
              Ime: this.form.get('ime')?.value,
              Pol: this.form.get('pol')?.value,
              Datum_rodjenja: this.form.get('datum')?.value,
              Kontakt: {
                Broj_mobilnog: this.form.get('brMobilni')?.value,
                Broj_fiksnog: this.form.get('brFiksni')?.value,
                Email: this.form.get('email')?.value,
              },
            },
            Radni_status: this.form.get('radniStatus')?.value,
            Zanimanje: this.form.get('zanimanjeZaposlenog')?.value,
            Koristi_soc_zastitu: {
              Vrednost: this.form.get('korisnikSZ')?.value,
              Opstina: this.form.get('opstinaSedistaZastite')?.value,
              Mestp: this.form.get('mestoSedistaZastite')?.value,
            },
            Izjava: {
              Vrednost: this.form.get('saglasan')?.value,
              Lek: this.form.get('vakcina')?.value,
            },
            Datum: moment(new Date()).format('YYYY-MM-DD'),
          },
          Radnikov_deo: {
            Zdravstvena_ustanova: '',
            Vakc_punkt: '',
            Podaci_lekar: {
              Ime: '',
              Prezime: '',
              Faksimil: '',
              Broj_telefona: '',
            },
            Vakcine: {},
            Privremene_nuspojave: '',
            Odluka: '',
          },
        },
      },
    };

    this.saglasnostService.create(obj).subscribe((result: any) => {
      this.parser.parseString(result, function (err: any, res: any) {});
    });
  }
}
