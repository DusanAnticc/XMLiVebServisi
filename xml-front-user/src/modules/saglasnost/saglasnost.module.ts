import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SaglasnostRoutingModule } from './saglasnost-routing.module';
import { MakeSaglasnostComponent } from './pages/make-saglasnost/make-saglasnost.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    MakeSaglasnostComponent
  ],
  imports: [
    CommonModule,
    SaglasnostRoutingModule,
    ReactiveFormsModule
  ]
})
export class SaglasnostModule { }
