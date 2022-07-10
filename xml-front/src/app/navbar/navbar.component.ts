import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  role: any = localStorage.getItem('role');
  constructor(private router: Router) {}

  ngOnInit(): void {}

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/auth/login']);
  }
}
