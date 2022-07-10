import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InteresovanjeRoutingModule } from './interesovanje-routing.module';
import { MakeInteresovanjeComponent } from './pages/make-interesovanje/make-interesovanje.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { DatePipe } from '@angular/common';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    MakeInteresovanjeComponent
  ],
  imports: [
    CommonModule,
    InteresovanjeRoutingModule,
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
export class InteresovanjeModule { }
