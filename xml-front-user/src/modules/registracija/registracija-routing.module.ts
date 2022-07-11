import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistracijaComponent } from './pages/registracija/registracija.component';

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    component: RegistracijaComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegistracijaRoutingModule { }
