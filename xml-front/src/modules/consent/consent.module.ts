import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateConsentComponent } from './pages/create-consent/create-consent.component';
import { ConsentRoutingModule } from './consent-routing.module';
import { ReactiveFormsModule } from "@angular/forms";

@NgModule({
  declarations: [
    CreateConsentComponent
  ],
  imports: [
    CommonModule,
    ConsentRoutingModule,
    ReactiveFormsModule
  ]
})
export class ConsentModule { }
