import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegistracijaRoutingModule } from './registracija-routing.module';
import { RegistracijaComponent } from './pages/registracija/registracija.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field'
import { ReactiveFormsModule } from "@angular/forms";
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    RegistracijaComponent
  ],
  imports: [
    CommonModule,
    RegistracijaRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule
  ]
})
export class RegistracijaModule { }