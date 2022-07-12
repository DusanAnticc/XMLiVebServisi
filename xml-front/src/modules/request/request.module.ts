import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PendingRequestsComponent } from './pages/pending-requests/pending-requests.component';
import { RequestRoutingModule } from './request-routing.module';
import { ResponseModalComponent } from './response-modal/response-modal.component';


@NgModule({
  declarations: [
    PendingRequestsComponent,
    ResponseModalComponent
  ],
  imports: [
    CommonModule,
    RequestRoutingModule
  ]
})
export class RequestModule { }
