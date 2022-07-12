import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RootLayoutComponent } from './root-layout/root-layout.component';

const routes: Routes = [
  {
    path: '',
    component: RootLayoutComponent,
    children: [
      {
        path: 'consent',
        loadChildren: () =>
          import('./../modules/consent/consent.module').then(
            (m) => m.ConsentModule
          ),
      },
      {
        path: 'report',
        loadChildren: () =>
          import('./../modules/report/report.module').then(
            (m) => m.ReportModule
          ),
      },
      {
        path: 'request',
        loadChildren: () =>
          import('./../modules/request/request.module').then(
            (m) => m.RequestModule
          ),
      },
      {
        path: 'confirmation',
        loadChildren: () =>
          import('./../modules/confirmation/confirmation.module').then(
            (m) => m.ConfirmationModule
          ),
      },
    ],
  },
  {
    path: 'prijava',
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./../modules/prijava/prijava.module').then(
            (m) => m.PrijavaModule
          ),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
