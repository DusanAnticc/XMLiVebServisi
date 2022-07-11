import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PendingRequestsComponent } from './pages/pending-requests/pending-requests.component';
import { RequestRoutingModule } from './request-routing.module';


@NgModule({
  declarations: [
    PendingRequestsComponent
  ],
  imports: [
    CommonModule,
    RequestRoutingModule
  ]
})
export class RequestModule { }
