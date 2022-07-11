import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PendingRequestsComponent } from './pages/pending-requests/pending-requests.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: PendingRequestsComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RequestRoutingModule {}
