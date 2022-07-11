import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegistracijaService } from '../../services/registracija.service';
import * as x2js from 'xml2js';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.scss']
})
export class RegistracijaComponent implements OnInit {
  form: FormGroup;
  parser = new x2js.Parser();

  constructor(private registracijaService: RegistracijaService) { 
    this.form = new FormGroup({
      ime: new FormControl(null, Validators.required),
      prezime: new FormControl(null, Validators.required),
      korisnickoIme: new FormControl(null, Validators.required),
      lozinka: new FormControl('', Validators.required),
      uloga: new FormControl([]),
      email: new FormControl(null, Validators.required),
    });}

  ngOnInit(): void {
  }

  submit(): void {
    this.form.patchValue({uloga: [{
      naziv: "ROLE_GRADJANIN"
    }]});
    const user = {
      korisnik: {
        ...this.form.value
      }
    }

    this.registracijaService.register(user).subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any,res: any){
        });
      }
    )
  }
}