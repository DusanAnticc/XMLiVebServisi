import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrijavaComponent } from './pages/prijava/prijava.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: PrijavaComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PrijavaRoutingModule {}
