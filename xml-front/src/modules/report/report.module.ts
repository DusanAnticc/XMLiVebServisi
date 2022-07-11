import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateReportComponent } from './pages/create-report/create-report.component';
import { ReportRoutingModule } from './report-routing.module';


@NgModule({
  declarations: [
    CreateReportComponent
  ],
  imports: [
    CommonModule,
    ReportRoutingModule
  ]
})
export class ReportModule { }
