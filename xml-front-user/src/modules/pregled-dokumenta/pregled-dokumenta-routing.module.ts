import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PregledDokumentaComponent } from './pages/pregled-dokumenta/pregled-dokumenta.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: PregledDokumentaComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PregledDokumentaRoutingModule { }
