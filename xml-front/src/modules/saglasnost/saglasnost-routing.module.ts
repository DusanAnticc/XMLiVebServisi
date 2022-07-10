import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SaglasnostComponent } from './saglasnost/saglasnost.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: SaglasnostComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SaglasnostRoutingModule {}
