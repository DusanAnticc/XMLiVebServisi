import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IzvestajService } from '../../services/izvestaj/izvestaj/izvestaj.service';

@Component({
  selector: 'app-make-izvestaj',
  templateUrl: './make-izvestaj.component.html',
  styleUrls: ['./make-izvestaj.component.scss'],
})
export class MakeIzvestajComponent implements OnInit {
  form: FormGroup;
  izvestajId: string = '';

  constructor(private izvestaService: IzvestajService) {
    this.form = new FormGroup({
      periodOd: new FormControl('', Validators.required),
      periodDo: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {}
}
