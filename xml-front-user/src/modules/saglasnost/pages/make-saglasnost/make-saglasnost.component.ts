import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as x2js from 'xml2js';
import { SaglasnostService } from '../../services/saglasnost.service';
import * as moment from 'moment';

@Component({
  selector: 'app-make-saglasnost',
  templateUrl: './make-saglasnost.component.html',
  styleUrls: ['./make-saglasnost.component.scss']
})
export class MakeSaglasnostComponent {
  form: FormGroup;
  parser = new x2js.Parser();
  opstine: any[] = [
    {value: 'Novi Sad'},
    {value: 'Beograd'},
    {value: 'Nis'},
  ];
  yesNo: any[] = [
    { value: 'Da' },
    { value: 'Ne' },
  ];

  saglasan: any[] = [
    {value: 'true',viewValue: 'SAGLASAN SAM'},
    {value: 'false',viewValue: 'NISAM SAGLASAN'},
  ];

  pol: any[] = [
    { value: 'Muski', viewValue: 'Muško' },
    { value: 'Zenski', viewValue: 'Žensko' },
  ];
  vaccines: any[] = [
    { value: 'Pfizer' },
    { value: 'Sputnik V' },
    { value: 'Sinopharm' },
    { value: 'AstraZeneca' },
    { value: 'Moderna' },
    { value: 'Bilo koja' },
  ];
  options: any[] = [
    {value:'srpsko', viewValue: 'Državljanin Republike Srbije'},
    {value: 'strano', viewValue: 'Strani državljanin'},
  ];
  workingStatus: any[] = [
    {value:'zaposlen', viewValue:'Zaposlen'},
    {value:'nezaposlen', viewValue:'Nezapsolen'},
    {value:'penzioner', viewValue:'Penzioner'},
    {value:'ucenik', viewValue:'Učenik'},
    {value:'student', viewValue:'Student'},
    {value:'dete', viewValue:'Dete'},
  ]
  jobRole: any[] = [
    {value:'zdra_zastita',viewValue: 'Zdravstvena zaštita'},
    {value:'soc_zastita', viewValue: 'Socijalna zaštita'},
    {value:'prosveta',viewValue: 'Prosveta'},
    {value:'mup',viewValue: 'MUP'},
    {value:'vojska',viewValue: 'Vojska RS'},
    {value:'drugo',viewValue: 'Drugo'},
  ]
  constructor( private saglasnostService:SaglasnostService) { 
     this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.pattern('[0-9 ]{13}')]),
      stranoDrzavljanstvo: new FormControl(''),
      brPasosa: new FormControl(''),
      imeRoditelja: new FormControl('',[Validators.required]),
      datum: new FormControl('', [Validators.required, Validators.pattern('[0-9 ]{4}-[0-9 ]{2}-[0-9]{2}')]),
      mestoRodjenja: new FormControl('',[Validators.required]),
      ime: new FormControl('', [Validators.required]),
      prezime: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      brMobilni: new FormControl('', [Validators.required, Validators.pattern('06[0-9 ]{8,9}')]),
      brFiksni: new FormControl('', [Validators.required, Validators.pattern('0[0-9 ]{8}')]),
      vakcina: new FormControl('', [Validators.required]),

      opstina: new FormControl('', [Validators.required]),
      naselje: new FormControl('',[Validators.required]),
      ulica: new FormControl('',[Validators.required]),
      broj: new FormControl('',[Validators.required]),
      opstinaSedistaZastite: new FormControl('',[]),
      mestoSedistaZastite: new FormControl('',[]),
      radniStatus: new FormControl('',[]),
      zanimanjeZaposlenog: new FormControl('',[]),
      korisnikSZ: new FormControl('',[Validators.required]),
      saglasan: new FormControl('',[Validators.required]),
      opcija: new FormControl(''),
      pol: new FormControl('',[Validators.required]),
      nazivLekara: new FormControl('',[Validators.required])
    });
  }
  
  submit(): void {
    var obj;
    
    obj = {
      Saglasnost : {
        '#': {
          Pacijentov_deo :{
              Drzavljanstvo: this.form.get('opcija'),
              Ime_roditelja: this.form.get('imeRoditelja')?.value,
              Mesto_rodjenja: this.form.get('mestoRodjenja')?.value,
              Adresa:{
                Ulica: this.form.get('ulicaBroj')?.value,
                Mesto: this.form.get('opstina')?.value,
                Broj: this.form.get('broj')?.value
              },
              Licni_podaci: {                
                Jmbg: this.form.get('jmbg')?.value,
                Prezime: this.form.get('prezime')?.value,
                Ime: this.form.get('ime')?.value,                
                Pol: this.form.get('pol')?.value,
                Datum_rodjenja: this.form.get('datum')?.value,                
                Kontakt:{
                  Broj_mobilnog: this.form.get('brMobilni')?.value,
                  Broj_fiksnog: this.form.get('brFiksni')?.value,
                  Email: this.form.get('email')?.value,
                }       
              },
              Radni_status: this.form.get('radniStatus')?.value,
                Zanimanje: this.form.get('zanimanjeZaposlenog')?.value,
                Koristi_soc_zastitu: {
                  Vrednost:  this.form.get('korisnikSZ')?.value,
                  Opstina: this.form.get('opstinaSedistaZastite')?.value,
                  Mestp: this.form.get('mestoSedistaZastite')?.value
                },
              Izjava:{
                Vrednost: this.form.get('saglasan')?.value,
                Lek: this.form.get('vakcina')?. value,
              },              
              Datum: (moment(new Date())).format('YYYY-MM-DD')
          }
        }
      }
    }
    
    this.saglasnostService.create(obj).subscribe(
      (result: any) => {
        this.parser.parseString(result, function (err: any, res: any) {
        });
      }
    )
  }


}