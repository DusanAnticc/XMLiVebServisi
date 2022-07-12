import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ZahtevRoutingModule } from './zahtev-routing.module';
import { MakeZahtevComponent } from './pages/make-zahtev/make-zahtev.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    MakeZahtevComponent
  ],
  imports: [
    CommonModule,
    ZahtevRoutingModule,
    ReactiveFormsModule
  ]
})
export class ZahtevModule { }
