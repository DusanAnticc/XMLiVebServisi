import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PrijavaService } from 'src/modules/prijava/services/prijava.service';

@Component({
  selector: 'app-root-layout',
  templateUrl: './root-layout.component.html',
  styleUrls: ['./root-layout.component.scss'],
})
export class RootLayoutComponent implements OnInit {
  constructor(private prijavaService: PrijavaService, private router: Router) {}

  ngOnInit(): void {
    console.log(this.prijavaService.getLoggedIn());

    if (
      !this.prijavaService.getLoggedIn() ||
      this.prijavaService.getLoggedIn()['ns2:Korisnik']['ns2:Uloga'][0] !==
        'gradjanin'
    )
      this.router.navigate(['/prijava']);
  }
}
