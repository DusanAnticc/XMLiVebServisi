import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateConfirmationComponent } from './pages/create-confirmation/create-confirmation.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: CreateConfirmationComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ConfirmationRoutingModule {}
