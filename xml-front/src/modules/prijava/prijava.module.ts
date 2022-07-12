import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrijavaRoutingModule } from './prijava-routing.module';
import { PrijavaComponent } from './pages/prijava/prijava.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [PrijavaComponent],
  imports: [
    CommonModule,
    PrijavaRoutingModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatTableModule,
    MatButtonModule,
  ],
})
export class PrijavaModule {}
