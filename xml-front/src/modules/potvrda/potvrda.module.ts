import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PotvrdaRoutingModule } from './potvrda-routing.module';
import { MakePotvrdaComponent } from './pages/make-potvrda/make-potvrda.component';

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
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
//import { ModalComponent } from './components/modal/modal.component'

@NgModule({
  declarations: [
    MakePotvrdaComponent,
    //ModalComponent
  ],
  imports: [
    CommonModule,
    PotvrdaRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatButtonToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
  ],
})
export class PotvrdaModule {}
