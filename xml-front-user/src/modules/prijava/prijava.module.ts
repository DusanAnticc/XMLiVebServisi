import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrijavaRoutingModule } from './prijava-routing.module';
import { PrijavaComponent } from './pages/prijava/prijava.component';
import { ReactiveFormsModule } from "@angular/forms";

@NgModule({
  declarations: [
    PrijavaComponent
  ],
  imports: [
    CommonModule,
    PrijavaRoutingModule,
    ReactiveFormsModule
  ]
})
export class PrijavaModule { }