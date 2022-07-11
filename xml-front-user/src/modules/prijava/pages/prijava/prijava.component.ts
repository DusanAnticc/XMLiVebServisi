import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PrijavaService } from '../../services/prijava.service';
import * as x2js from 'xml2js';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prijava',
  templateUrl: './prijava.component.html',
  styleUrls: ['./prijava.component.scss']
})

export class PrijavaComponent implements OnInit {
  form: FormGroup;
  parser = new x2js.Parser();

  constructor(private prijavaService: PrijavaService, private router: Router) { 
    this.form = new FormGroup({
      email: new FormControl(null, Validators.required),
      lozinka: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    var obj = {
      Prijava_dto: {
        '#': {
          email:this.form.get('username')?.value,
          lozinka: this.form.get('password')?.value,
        },
      },
    };
      this.prijavaService.login(obj).subscribe(
        (result) => {
          this.parser.parseString(result, function(err: any,res: any){          
        },
      );
    }
      )
  }
}