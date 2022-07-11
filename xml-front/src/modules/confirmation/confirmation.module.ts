import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateConfirmationComponent } from './pages/create-confirmation/create-confirmation.component';
import { ConfirmationRoutingModule } from './confirmation-routing.module';


@NgModule({
  declarations: [
    CreateConfirmationComponent
  ],
  imports: [
    CommonModule,
    ConfirmationRoutingModule
  ]
})
export class ConfirmationModule { }
