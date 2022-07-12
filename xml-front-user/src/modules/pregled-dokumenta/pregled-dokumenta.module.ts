import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PregledDokumentaRoutingModule } from './pregled-dokumenta-routing.module';
import { PregledDokumentaComponent } from './pages/pregled-dokumenta/pregled-dokumenta.component';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field'
import { ReactiveFormsModule } from "@angular/forms";
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    PregledDokumentaComponent,
  ],
  imports: [
    CommonModule,
    PregledDokumentaRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule
  ]
})
export class PregledDokumentaModule { }
