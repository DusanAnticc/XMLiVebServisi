import { Component } from '@angular/core';
import { ReportService } from '../../service/report.service';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import * as x2js from 'xml2js';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.scss']
})
export class CreateReportComponent {
  form: FormGroup;
  parser = new x2js.Parser();

  constructor(
    private reportService: ReportService
  ) {
    this.form = new FormGroup({
      periodOd: new FormControl('', Validators.required),
      periodDo: new FormControl('', Validators.required),
    });
  }

  submit() {

  }

  getHtml() {

  }

  getPdf() {

  }

  getRdf() {

  }

  getJson() {

  }

}
