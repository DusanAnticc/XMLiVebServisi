import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InteresovanjeRoutingModule } from './interesovanje-routing.module';
import { MakeInteresovanjeComponent } from './pages/make-interesovanje/make-interesovanje.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    MakeInteresovanjeComponent
  ],
  imports: [
    CommonModule,
    InteresovanjeRoutingModule,
    ReactiveFormsModule
  ]
})
export class InteresovanjeModule { }
