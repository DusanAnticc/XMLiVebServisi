import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateReportComponent } from './pages/create-report/create-report.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: CreateReportComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReportRoutingModule {}
