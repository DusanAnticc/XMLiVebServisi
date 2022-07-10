import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MakeInteresovanjeComponent } from './pages/make-interesovanje/make-interesovanje.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: MakeInteresovanjeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InteresovanjeRoutingModule { }
