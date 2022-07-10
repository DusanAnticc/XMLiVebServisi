import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RootLayoutComponent } from './root-layout/root-layout.component';
const routes: Routes = [
  {
    path: '',
    component: RootLayoutComponent,
    children: [
      {
        path: 'zahtev',
        loadChildren: () =>
          import('./../modules/zahtev/zahtev.module').then(
            (m) => m.ZahtevModule
          ),
      },
      {
        path: 'saglasnost',
        loadChildren: () =>
          import('./../modules/saglasnost/saglasnost.module').then(
            (m) => m.SaglasnostModule
          ),
      },
      {
        path: 'interesovanje',
        loadChildren: () =>
          import('./../modules/interesovanje/interesovanje.module').then(
            (m) => m.InteresovanjeModule
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
