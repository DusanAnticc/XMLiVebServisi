import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

//import { ModalComponent } from '../../components/modal/modal.component';
import { Doza } from '../../Doza';
import { PotvrdaService } from '../../services/potvrda.service';

@Component({
  selector: 'app-make-potvrda',
  templateUrl: './make-potvrda.component.html',
  styleUrls: ['./make-potvrda.component.scss'],
})
export class MakePotvrdaComponent implements OnInit {
  form: FormGroup;
  gender: any[] = [
    { value: 'Musko', viewValue: 'Muško' },
    { value: 'Zensko', viewValue: 'Žensko' },
  ];
  vaccines: any[] = [
    { value: 'Pfizer-BioNtech' },
    { value: 'Sputnik V' },
    { value: 'Sinopharm' },
    { value: 'AstraZeneca' },
    { value: 'Moderna' },
  ];
  doze: Doza[] = [];
  displayedColumns: string[] = ['datum_davanja', 'serija'];

  constructor(
    private potvrdaService: PotvrdaService,
    public dialog: MatDialog
  ) {
    this.form = new FormGroup({
      imePrezime: new FormControl('', [Validators.required]),
      datumRodjenja: new FormControl('', [Validators.required]),
      pol: new FormControl('', [Validators.required]),
      jmbg: new FormControl('', [
        Validators.pattern('[0-9]{13}'),
        Validators.required,
      ]),
      vakcina: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {}

  submit(): void {}

  openCompDialog(): void {}
}
