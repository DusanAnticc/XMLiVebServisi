import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MakeSaglasnostComponent } from './pages/make-saglasnost/make-saglasnost.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: MakeSaglasnostComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SaglasnostRoutingModule { }
