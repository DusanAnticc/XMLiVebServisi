import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RootLayoutComponent } from './root-layout/root-layout.component';

const routes: Routes = [
  {
    path: '',
    component: RootLayoutComponent,
    children: [
      {
        path: 'saglasnost',
        loadChildren: () =>
          import('./../modules/saglasnost/saglasnost.module').then(
            (m) => m.SaglasnostModule
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
        path: 'zahtev',
        loadChildren: () =>
          import('./../modules/zahtev/zahtev.module').then(
            (m) => m.ZahtevModule
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
  // {
  //   path: 'auth',
  //   children: [
  //     {
  //       path: '',
  //       loadChildren: () =>
  //         import('./../auth/auth.module').then((m) => m.AuthModule),
  //     },
  //   ],
  // },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
