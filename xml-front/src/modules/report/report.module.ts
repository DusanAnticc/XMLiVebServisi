import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateReportComponent } from './pages/create-report/create-report.component';
import { ReportRoutingModule } from './report-routing.module';
import { ReactiveFormsModule } from "@angular/forms";

@NgModule({
  declarations: [
    CreateReportComponent
  ],
  imports: [
    CommonModule,
    ReportRoutingModule,
    ReactiveFormsModule
  ]
})
export class ReportModule { }
