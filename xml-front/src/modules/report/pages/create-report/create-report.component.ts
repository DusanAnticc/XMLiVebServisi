import { Component } from '@angular/core';
import { ReportService } from '../../service/report.service';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.scss']
})
export class CreateReportComponent {

  constructor(
    private reportService: ReportService
  ) {
  }

  create() {
    const startDate = (<HTMLInputElement>document.getElementById("startDate")).value;
    const endDate = (<HTMLInputElement>document.getElementById("endDate")).value;
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
