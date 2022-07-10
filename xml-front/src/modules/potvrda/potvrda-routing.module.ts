import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MakePotvrdaComponent } from './pages/make-potvrda/make-potvrda.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: MakePotvrdaComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PotvrdaRoutingModule {}
