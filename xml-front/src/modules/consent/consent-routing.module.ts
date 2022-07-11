import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateConsentComponent } from './pages/create-consent/create-consent.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: CreateConsentComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ConsentRoutingModule {}
