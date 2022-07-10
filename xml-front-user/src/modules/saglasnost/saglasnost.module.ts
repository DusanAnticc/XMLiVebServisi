import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SaglasnostRoutingModule } from './saglasnost-routing.module';
import { MakeSaglasnostComponent } from './pages/make-saglasnost/make-saglasnost.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    MakeSaglasnostComponent
  ],
  imports: [
    CommonModule,
    SaglasnostRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatButtonToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ]
})
export class SaglasnostModule { }
