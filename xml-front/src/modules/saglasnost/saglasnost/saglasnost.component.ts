import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  Validators,
} from '@angular/forms';
import { SaglasnostService } from '../services/saglasnost.service';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-saglasnost',
  templateUrl: './saglasnost.component.html',
  styleUrls: ['./saglasnost.component.scss'],
})
export class SaglasnostComponent implements OnInit {
  form: FormGroup;
  vaccines: any[] = [
    { value: 'Pfizer-BioNtech' },
    { value: 'Sputnik V' },
    { value: 'Sinopharm' },
    { value: 'AstraZeneca' },
    { value: 'Moderna' },
    { value: 'Bilo koja' },
  ];
  ekstremitet: any[] = [
    { value: 'DR', viewValue: 'Desna ruka' },
    { value: 'LR', viewValue: 'Leva ruka' },
  ];
  constructor(
    private saglasnostService: SaglasnostService,
    public datepipe: DatePipe
  ) {
    this.form = new FormGroup({
      jmbg: new FormControl('', [Validators.pattern('[0-9 ]{13}')]),
      zdravstvenaUstanova: new FormControl('', [Validators.required]),
      vakcinacijskiPunkt: new FormControl('', [Validators.required]),
      podaciOLekaru: new FormControl('', [Validators.required]),
      vakcina: new FormControl('', [Validators.required]),
      datumDavanjaVakcine: new FormControl(new Date(), [Validators.required]),
      nacinDavanjaVakcine: new FormControl('IM', [Validators.required]),
      ekstremitet: new FormControl('', [Validators.required]),
      serijaVakcine: new FormControl('', [Validators.required]),
      proizvodjac: new FormControl('', [Validators.required]),
      nezeljenaReakcija: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {}

  submit() {}
}
