import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateConsentComponent } from './pages/create-consent/create-consent.component';
import { ConsentRoutingModule } from './consent-routing.module';


@NgModule({
  declarations: [
    CreateConsentComponent
  ],
  imports: [
    CommonModule,
    ConsentRoutingModule
  ]
})
export class ConsentModule { }
